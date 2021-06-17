package coop.home.bankaccount.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.home.bankaccount.dto.TokenBankAccountDTO;
import coop.home.bankaccount.dto.accounts.AccountIDBalanceDTO;
import coop.home.bankaccount.dto.accounttransfer.TransferDTO;
import coop.home.bankaccount.dto.accounttransfer.TransferDoneDTO;
import coop.home.bankaccount.excepcion.BusinessException;
import coop.home.bankaccount.repository.IAccountsRepository;
import coop.home.bankaccount.repository.IAccountsTransferRepository;
import coop.home.bankaccount.repository.entity.Accounts;
import coop.home.bankaccount.repository.entity.AccountsPK;
import coop.home.bankaccount.repository.entity.Accounttransfers;
import coop.home.bankaccount.repository.entity.AccounttransfersPK;
import coop.home.bankaccount.service.AccountsService;
import coop.home.bankaccount.service.AccountsTransferService;
import coop.home.bankaccount.service.CurrencyConversionService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
@PropertySource("classpath:application.properties")
class AccountsTransferServiceImpl implements AccountsTransferService {
	
	@Autowired
	private CurrencyConversionService currencyConversionService;
	
	@Autowired
	private IAccountsTransferRepository accountsTransferRepository;
	
	@Autowired
	private IAccountsRepository accountsRepository;
	
	@Autowired
	private AccountsService accountsService;
	
	@Autowired
	private MessageSource mensajes;
	
	@Value("${exchangerates.defaultsymbol}")
	private String defaultSymbol;


	public TransferDoneDTO transfer(TransferDTO transferDTO, TokenBankAccountDTO tokenBankAccount) {
		
		
		String loginUser = tokenBankAccount.getLoginUser();
		
		Accounts sourceAccount = this.validationAcauntOwner(transferDTO, loginUser);
		
		this.validationRecipientAccountExist(transferDTO);

		BigDecimal amountUSD = currencyConversionService.getUSConversion(transferDTO.getTransferSymbol(),transferDTO.getTransferAmount());
		
		this.validationSufficientfunds(sourceAccount, amountUSD);
		
		
		Date actualDate = new Date();
		
		this.tranferRegistration(actualDate, transferDTO,amountUSD);
		
		accountsService.credit(
				new AccountsPK(transferDTO.getSourceAccount().getIdaccount(),
						transferDTO.getSourceAccount().getUsersidfinancialcompany())
				, amountUSD);
		
		accountsService.debit(
				new AccountsPK(transferDTO.getRecipientAccount().getIdaccount(),
						transferDTO.getRecipientAccount().getUsersidfinancialcompany())
				, amountUSD);
		
		Accounts recipientAccountFinal= accountsRepository.getById(new AccountsPK(transferDTO.getRecipientAccount().getIdaccount(),transferDTO.getRecipientAccount().getUsersidfinancialcompany()));
		Accounts sourceAccountFinal= accountsRepository.getById(new AccountsPK(transferDTO.getSourceAccount().getIdaccount(),transferDTO.getSourceAccount().getUsersidfinancialcompany()));
		

		return new TransferDoneDTO(
				new AccountIDBalanceDTO(sourceAccountFinal.getAccountsPK().getUsersidfinancialcompany(),sourceAccountFinal.getAccountsPK().getIdaccount(),sourceAccountFinal.getBalance()),
				new AccountIDBalanceDTO(recipientAccountFinal.getAccountsPK().getUsersidfinancialcompany(),recipientAccountFinal.getAccountsPK().getIdaccount(),recipientAccountFinal.getBalance()),
				transferDTO.getTransferAmount(),
				transferDTO.getTransferSymbol(),
				transferDTO.getTransferDescripcion(),
				actualDate,
				amountUSD,
				defaultSymbol);
	}
	
	private Accounts validationAcauntOwner(TransferDTO transferDTO,String loginUser) {
		List<Accounts> userAccounts = accountsRepository.findByAccountsPK_usersidfinancialcompanyAndFinancialusers_FinancialusersPK_loginuser(
				transferDTO.getSourceAccount().getUsersidfinancialcompany(), 
				loginUser);
		
		Accounts sourceAccount = null;
		boolean accountUserSource = false;
		for(Accounts A : userAccounts) {
			if(A.getAccountsPK().getIdaccount().equals(transferDTO.getSourceAccount().getIdaccount())) {
				accountUserSource= true;
				sourceAccount = A;
				break;
			}
		}
		if(!accountUserSource){
			throw new BusinessException(mensajes.getMessage("app.transfer.account.noownwe.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.transfer.account.noownwe.message", null, LocaleContextHolder.getLocale()));
		}
		
		return sourceAccount;
	}
	
	private void validationRecipientAccountExist(TransferDTO transferDTO) {
		
		
		Accounts recipientAccount = accountsRepository.getById(new AccountsPK(
				transferDTO.getRecipientAccount().getIdaccount(),
				transferDTO.getRecipientAccount().getUsersidfinancialcompany()));
		
		try {
			if(recipientAccount.getBalance()==null){
				throw new BusinessException(mensajes.getMessage("app.transfer.account.recipientdotexist.code", null, LocaleContextHolder.getLocale()),
						mensajes.getMessage("app.transfer.account.recipientdotexist.message", null, LocaleContextHolder.getLocale()));
			
			}
		}catch (EntityNotFoundException e) {
			throw new BusinessException(mensajes.getMessage("app.transfer.account.recipientdotexist.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.transfer.account.recipientdotexist.message", null, LocaleContextHolder.getLocale()));
		}
		
	}
	
	private void validationSufficientfunds(Accounts sourceAccount,BigDecimal amountUSD) {
				
		if(sourceAccount.getBalance().compareTo(amountUSD)<0){
			throw new BusinessException(mensajes.getMessage("app.transfer.account.insufficientfunds.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.transfer.account.insufficientfunds.message", null, LocaleContextHolder.getLocale()));
		}
	}
	
	private void tranferRegistration(Date actualDate, TransferDTO transferDTO,BigDecimal amountUSD) {
		
		 Timestamp ts=new Timestamp(actualDate.getTime());  
		 
		 log.info("Timestamp {}",ts.toString());
		
		AccounttransfersPK accounttransfersPK = new AccounttransfersPK(ts.toString(),
				transferDTO.getRecipientAccount().getUsersidfinancialcompany(),
				transferDTO.getRecipientAccount().getIdaccount(),
				transferDTO.getSourceAccount().getUsersidfinancialcompany(),
				transferDTO.getSourceAccount().getIdaccount());
		
		Accounttransfers accounttransfers = new Accounttransfers(accounttransfersPK,
				amountUSD,
				transferDTO.getTransferDescripcion(), 
				transferDTO.getTransferSymbol(),
				transferDTO.getTransferAmount());
		
		accountsTransferRepository.save(accounttransfers);
	}
}
