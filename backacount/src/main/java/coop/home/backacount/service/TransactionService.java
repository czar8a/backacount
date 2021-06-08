package coop.home.backacount.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.home.backacount.dto.TokenBackAcountDTO;
import coop.home.backacount.dto.accounts.AccountIDDTO;
import coop.home.backacount.dto.transaction.TransactionDTO;
import coop.home.backacount.excepcion.BusinessException;
import coop.home.backacount.excepcion.UnAuthorizedException;
import coop.home.backacount.repository.IAccountsRepository;
import coop.home.backacount.repository.ITransactionsRepository;
import coop.home.backacount.repository.entity.Accounts;
import coop.home.backacount.repository.entity.Transactions;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {
	
	@Autowired
	private ITransactionsRepository transactionsRepository;
	
	@Autowired
	private IAccountsRepository accountsRepository;

	@Autowired
	private MessageSource mensajes;


	public List<TransactionDTO> getAcountTransactions(BigInteger usersidfinancialcompany, String idacount, TokenBackAcountDTO tokenBackAcount) {
		
		Accounts account =accountsRepository.findByAccountsPK_usersidfinancialcompanyAndAccountsPK_idacount(usersidfinancialcompany, idacount);
		
		if(account==null) {
			throw new BusinessException(mensajes.getMessage("app.transaction.noexistingacount.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.transaction.noexistingacount.mesage", null, LocaleContextHolder.getLocale()));
		}
		
		if(!account.getFinancialusers().getFinancialusersPK().getLoginuser().equals(tokenBackAcount.getLoginUser())) {
			throw new UnAuthorizedException(mensajes.getMessage("app.security.acountowner.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.security.acountowner.mesage", null, LocaleContextHolder.getLocale()));
		}
		
		
		List<Transactions> transactions =  transactionsRepository.findByTransactionsPK_accountidfinancialcompanyAndTransactionsPK_idacount(usersidfinancialcompany, idacount);
		
		return this.map(transactions);
	}
	
	private List<TransactionDTO> map(List<Transactions> transactions) {
		
		List<TransactionDTO> transactionsDTO = new ArrayList<TransactionDTO>();
		
		for(Transactions T : transactions) {
			transactionsDTO.add(new TransactionDTO(T.getTransactionsPK().getAccountidfinancialcompany(),
					T.getTransactionsPK().getIdacount(),
					T.getTransactiontypes().getTransactionname(),
					T.getTransactionsPK().getTransactiondate(),
					T.getAmount()));
		}
		
		return transactionsDTO;
	}
	
	
}
