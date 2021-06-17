package coop.home.bankaccount.service;

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
import lombok.extern.slf4j.Slf4j;


public interface TransactionService {
	
	public List<TransactionDTO> getAccountTransactions(BigInteger usersidfinancialcompany, String idaccount, TokenBankAccountDTO tokenBankAccount) ;
	
	
}
