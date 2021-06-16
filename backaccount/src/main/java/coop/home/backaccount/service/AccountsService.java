package coop.home.backaccount.service;

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

import coop.home.backaccount.dto.TokenBackAcountDTO;
import coop.home.backaccount.dto.accounts.AccountsDTO;
import coop.home.backaccount.excepcion.UnAuthorizedException;
import coop.home.backaccount.repository.IAccountsRepository;
import coop.home.backaccount.repository.ITransactionsRepository;
import coop.home.backaccount.repository.entity.Accounts;
import coop.home.backaccount.repository.entity.AccountsPK;
import coop.home.backaccount.repository.entity.Transactions;
import coop.home.backaccount.repository.entity.TransactionsPK;
import coop.home.backaccount.util.CustomDateFunctions;
import lombok.extern.slf4j.Slf4j;

public interface AccountsService {

	public List<AccountsDTO> getAccounts(BigInteger idfinancialcompany, String loginuser,TokenBackAcountDTO tokenBackAcountDTO);
	
	public void credit(AccountsPK accountsPK, BigDecimal creditValue) ;
	
	public void debit(AccountsPK accountsPK, BigDecimal debitValue) ;
	
	
}
