package coop.home.backacount.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.home.backacount.dto.TokenBackAcountDTO;
import coop.home.backacount.dto.accounts.AccountsDTO;
import coop.home.backacount.excepcion.UnAuthorizedException;
import coop.home.backacount.repository.IAccountsRepository;
import coop.home.backacount.repository.ITransactionsRepository;
import coop.home.backacount.repository.entity.Accounts;
import coop.home.backacount.repository.entity.AccountsPK;
import coop.home.backacount.repository.entity.Transactions;
import coop.home.backacount.repository.entity.TransactionsPK;
import coop.home.backacount.service.AccountsService;
import coop.home.backacount.util.CustomDateFunctions;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
class AccountsServiceImpl implements AccountsService {

	@Autowired
	private IAccountsRepository accountsRepository;
	
	@Autowired
	private ITransactionsRepository transactionsRepository;
	
	@Autowired
	private MessageSource mensajes;

	public List<AccountsDTO> getAccounts(BigInteger idfinancialcompany, String loginuser,TokenBackAcountDTO tokenBackAcountDTO) {
		
		if(!tokenBackAcountDTO.getLoginUser().equals(loginuser)) {
			throw new UnAuthorizedException(mensajes.getMessage("app.security.acountowner.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.security.acountowner.mesage", null, LocaleContextHolder.getLocale()));
		}
		
		
		List<AccountsDTO> accounts = new ArrayList<>();
		for(Accounts u : accountsRepository.
				findByAccountsPK_usersidfinancialcompanyAndFinancialusers_FinancialusersPK_loginuser(
						idfinancialcompany,loginuser)) {
			if(u!=null) {
				accounts.add(new AccountsDTO(u.getAccountsPK().getIdacount(),
						u.getAccountsPK().getUsersidfinancialcompany(),
						u.getFinancialusers().getFinancialusersPK().getLoginuser(),
						u.getBalance()));
			}
		}
		return accounts;
	}
	
	public void credit(AccountsPK accountsPK, BigDecimal creditValue) {
		this.acountMovement(accountsPK, creditValue, true);
	}
	
	public void debit(AccountsPK accountsPK, BigDecimal debitValue) {
		this.acountMovement(accountsPK, debitValue, false);
	}
	
	private void acountMovement(AccountsPK accountsPK, BigDecimal creditValue, boolean credit) {
		
		Optional<Accounts> accounts = accountsRepository.findById(accountsPK);
		
		if(accounts.isPresent()) {
			Accounts account = accounts.get();
			
			BigInteger transactionType;
			if(credit) {
				account.setBalance(account.getBalance().subtract(creditValue));
				transactionType = BigInteger.valueOf(1);
			}
			else {
				account.setBalance(account.getBalance().add(creditValue));
				transactionType = BigInteger.valueOf(2);
			}
			
			
			TransactionsPK transactionsPK = new  TransactionsPK(account.getAccountsPK().getUsersidfinancialcompany(),
					account.getAccountsPK().getIdacount(),
					transactionType,
					this.getTimeStamp());
			
			Transactions transactions = new Transactions(transactionsPK,creditValue);
			
			transactionsRepository.save(transactions);
			
			accountsRepository.save(account);
			
		}
	}
	
	private String getTimeStamp() {
		return CustomDateFunctions.getTimeStampWithMinutes(new Date());
	}
}
