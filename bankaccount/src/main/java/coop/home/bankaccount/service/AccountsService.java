package coop.home.bankaccount.service;

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
import coop.home.bankaccount.util.CustomDateFunctions;
import lombok.extern.slf4j.Slf4j;

public interface AccountsService {

	public List<AccountsDTO> getAccounts(BigInteger idfinancialcompany, String loginuser,TokenBankAccountDTO tokenBankAccountDTO);
	
	public void credit(AccountsPK accountsPK, BigDecimal creditValue) ;
	
	public void debit(AccountsPK accountsPK, BigDecimal debitValue) ;
	
	
}
