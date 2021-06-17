package coop.home.bankaccount.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.home.bankaccount.dto.TokenBankAccountDTO;
import coop.home.bankaccount.dto.accounts.AccountIDDTO;
import coop.home.bankaccount.dto.transaction.TransactionDTO;
import coop.home.bankaccount.excepcion.BusinessException;
import coop.home.bankaccount.excepcion.UnAuthorizedException;
import coop.home.bankaccount.repository.IAccountsRepository;
import coop.home.bankaccount.repository.ITransactionsRepository;
import coop.home.bankaccount.repository.entity.Accounts;
import coop.home.bankaccount.repository.entity.Transactions;
import coop.home.bankaccount.service.TransactionService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private ITransactionsRepository transactionsRepository;
	
	@Autowired
	private IAccountsRepository accountsRepository;

	@Autowired
	private MessageSource mensajes;


	public List<TransactionDTO> getAccountTransactions(BigInteger usersidfinancialcompany, String idaccount, TokenBankAccountDTO tokenBankAccount) {
		
		Accounts account =accountsRepository.findByAccountsPK_usersidfinancialcompanyAndAccountsPK_idaccount(usersidfinancialcompany, idaccount);
		
		if(account==null) {
			throw new BusinessException(mensajes.getMessage("app.transaction.noexistingaccount.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.transaction.noexistingaccount.message", null, LocaleContextHolder.getLocale()));
		}
		
		if(!account.getFinancialusers().getFinancialusersPK().getLoginuser().equals(tokenBankAccount.getLoginUser())) {
			throw new UnAuthorizedException(mensajes.getMessage("app.security.accountowner.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.security.accountowner.message", null, LocaleContextHolder.getLocale()));
		}
		
		
		List<Transactions> transactions =  transactionsRepository.findByTransactionsPK_accountidfinancialcompanyAndTransactionsPK_idaccount(usersidfinancialcompany, idaccount);
		
		return this.map(transactions);
	}
	
	private List<TransactionDTO> map(List<Transactions> transactions) {
		
		List<TransactionDTO> transactionsDTO = new ArrayList<TransactionDTO>();
		
		for(Transactions T : transactions) {
			transactionsDTO.add(new TransactionDTO(T.getTransactionsPK().getAccountidfinancialcompany(),
					T.getTransactionsPK().getIdaccount(),
					T.getTransactiontypes().getTransactionname(),
					T.getTransactionsPK().getTransactiondate(),
					T.getAmount()));
		}
		
		return transactionsDTO;
	}
	
	
}
