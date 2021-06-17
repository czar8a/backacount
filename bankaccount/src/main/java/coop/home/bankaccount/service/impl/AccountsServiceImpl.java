package coop.home.bankaccount.service.impl;

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

import coop.home.bankaccount.dto.TokenBankAccountDTO;
import coop.home.bankaccount.dto.accounts.AccountsDTO;
import coop.home.bankaccount.excepcion.UnAuthorizedException;
import coop.home.bankaccount.repository.IAccountsRepository;
import coop.home.bankaccount.repository.ITransactionsRepository;
import coop.home.bankaccount.repository.entity.Accounts;
import coop.home.bankaccount.repository.entity.AccountsPK;
import coop.home.bankaccount.repository.entity.Transactions;
import coop.home.bankaccount.repository.entity.TransactionsPK;
import coop.home.bankaccount.service.AccountsService;
import coop.home.bankaccount.util.CustomDateFunctions;
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

	public List<AccountsDTO> getAccounts(BigInteger idfinancialcompany, String loginuser,TokenBankAccountDTO tokenBankAccountDTO) {
		
		if(!tokenBankAccountDTO.getLoginUser().equals(loginuser)) {
			throw new UnAuthorizedException(mensajes.getMessage("app.security.accountowner.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.security.accountowner.message", null, LocaleContextHolder.getLocale()));
		}
		
		
		List<AccountsDTO> accounts = new ArrayList<>();
		for(Accounts u : accountsRepository.
				findByAccountsPK_usersidfinancialcompanyAndFinancialusers_FinancialusersPK_loginuser(
						idfinancialcompany,loginuser)) {
			if(u!=null) {
				accounts.add(new AccountsDTO(u.getAccountsPK().getIdaccount(),
						u.getAccountsPK().getUsersidfinancialcompany(),
						u.getFinancialusers().getFinancialusersPK().getLoginuser(),
						u.getBalance()));
			}
		}
		return accounts;
	}
	
	public void credit(AccountsPK accountsPK, BigDecimal creditValue) {
		this.accountMovement(accountsPK, creditValue, true);
	}
	
	public void debit(AccountsPK accountsPK, BigDecimal debitValue) {
		this.accountMovement(accountsPK, debitValue, false);
	}
	
	private void accountMovement(AccountsPK accountsPK, BigDecimal creditValue, boolean credit) {
		
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
					account.getAccountsPK().getIdaccount(),
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
