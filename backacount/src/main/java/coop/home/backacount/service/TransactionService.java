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


public interface TransactionService {
	
	public List<TransactionDTO> getAcountTransactions(BigInteger usersidfinancialcompany, String idacount, TokenBackAcountDTO tokenBackAcount) ;
	
	
}
