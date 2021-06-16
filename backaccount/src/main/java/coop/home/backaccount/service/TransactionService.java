package coop.home.backaccount.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.home.backaccount.dto.TokenBackAcountDTO;
import coop.home.backaccount.dto.accounts.AccountIDDTO;
import coop.home.backaccount.dto.transaction.TransactionDTO;
import coop.home.backaccount.excepcion.BusinessException;
import coop.home.backaccount.excepcion.UnAuthorizedException;
import coop.home.backaccount.repository.IAccountsRepository;
import coop.home.backaccount.repository.ITransactionsRepository;
import coop.home.backaccount.repository.entity.Accounts;
import coop.home.backaccount.repository.entity.Transactions;
import lombok.extern.slf4j.Slf4j;


public interface TransactionService {
	
	public List<TransactionDTO> getAcountTransactions(BigInteger usersidfinancialcompany, String idacount, TokenBackAcountDTO tokenBackAcount) ;
	
	
}
