package coop.home.backacount.service.impl;

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

import coop.home.backacount.dto.TokenBackAcountDTO;
import coop.home.backacount.dto.accounts.AccountIDBalanceDTO;
import coop.home.backacount.dto.acouttransfer.TransferDTO;
import coop.home.backacount.dto.acouttransfer.TransferDoneDTO;
import coop.home.backacount.excepcion.BusinessException;
import coop.home.backacount.repository.IAccountsRepository;
import coop.home.backacount.repository.IAccountsTransferRepository;
import coop.home.backacount.repository.entity.Accounts;
import coop.home.backacount.repository.entity.AccountsPK;
import coop.home.backacount.repository.entity.Accounttransfers;
import coop.home.backacount.repository.entity.AccounttransfersPK;
import coop.home.backacount.service.AccountsService;
import coop.home.backacount.service.AccountsTransferService;
import coop.home.backacount.service.CurrencyConversionService;
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


	public TransferDoneDTO transfer(TransferDTO transferDTO, TokenBackAcountDTO tokenBackAcount) {
		
		
		String loginUser = tokenBackAcount.getLoginUser();
		
		Accounts sourceAcount = this.validationAcauntOwner(transferDTO, loginUser);
		
		this.validationRecipientAcountExist(transferDTO);

		BigDecimal amountUSD = currencyConversionService.getUSConvertion(transferDTO.getTransferSymbol(),transferDTO.getTransferAmount());
		
		this.validationSufficientfunds(sourceAcount, amountUSD);
		
		
		Date actualDate = new Date();
		
		this.tranferRegistration(actualDate, transferDTO,amountUSD);
		
		accountsService.credit(
				new AccountsPK(transferDTO.getSourceAcount().getIdacount(),
						transferDTO.getSourceAcount().getUsersidfinancialcompany())
				, amountUSD);
		
		accountsService.debit(
				new AccountsPK(transferDTO.getRecipientAcount().getIdacount(),
						transferDTO.getRecipientAcount().getUsersidfinancialcompany())
				, amountUSD);
		
		Accounts recipientAcountFinal= accountsRepository.getById(new AccountsPK(transferDTO.getRecipientAcount().getIdacount(),transferDTO.getRecipientAcount().getUsersidfinancialcompany()));
		Accounts sourceAcountFinal= accountsRepository.getById(new AccountsPK(transferDTO.getSourceAcount().getIdacount(),transferDTO.getSourceAcount().getUsersidfinancialcompany()));
		

		return new TransferDoneDTO(
				new AccountIDBalanceDTO(sourceAcountFinal.getAccountsPK().getUsersidfinancialcompany(),sourceAcountFinal.getAccountsPK().getIdacount(),sourceAcountFinal.getBalance()),
				new AccountIDBalanceDTO(recipientAcountFinal.getAccountsPK().getUsersidfinancialcompany(),recipientAcountFinal.getAccountsPK().getIdacount(),recipientAcountFinal.getBalance()),
				transferDTO.getTransferAmount(),
				transferDTO.getTransferSymbol(),
				transferDTO.getTransferDescripcion(),
				actualDate,
				amountUSD,
				defaultSymbol);
	}
	
	private Accounts validationAcauntOwner(TransferDTO transferDTO,String loginUser) {
		List<Accounts> userAcounts = accountsRepository.findByAccountsPK_usersidfinancialcompanyAndFinancialusers_FinancialusersPK_loginuser(
				transferDTO.getSourceAcount().getUsersidfinancialcompany(), 
				loginUser);
		
		Accounts sourceAcount = null;
		boolean acountUserSource = false;
		for(Accounts A : userAcounts) {
			if(A.getAccountsPK().getIdacount().equals(transferDTO.getSourceAcount().getIdacount())) {
				acountUserSource= true;
				sourceAcount = A;
				break;
			}
		}
		if(!acountUserSource){
			throw new BusinessException(mensajes.getMessage("app.trasfer.accoutn.noownwe.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.trasfer.accoutn.noownwe.mesage", null, LocaleContextHolder.getLocale()));
		}
		
		return sourceAcount;
	}
	
	private void validationRecipientAcountExist(TransferDTO transferDTO) {
		
		
		Accounts recipientAcount = accountsRepository.getById(new AccountsPK(
				transferDTO.getRecipientAcount().getIdacount(),
				transferDTO.getRecipientAcount().getUsersidfinancialcompany()));
		
		try {
			if(recipientAcount.getBalance()==null){
				throw new BusinessException(mensajes.getMessage("app.trasfer.accoutn.recipientdotexist.code", null, LocaleContextHolder.getLocale()),
						mensajes.getMessage("app.trasfer.accoutn.recipientdotexist.mesage", null, LocaleContextHolder.getLocale()));
			
			}
		}catch (EntityNotFoundException e) {
			throw new BusinessException(mensajes.getMessage("app.trasfer.accoutn.recipientdotexist.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.trasfer.accoutn.recipientdotexist.mesage", null, LocaleContextHolder.getLocale()));
		}
		
	}
	
	private void validationSufficientfunds(Accounts sourceAcount,BigDecimal amountUSD) {
				
		if(sourceAcount.getBalance().compareTo(amountUSD)<0){
			throw new BusinessException(mensajes.getMessage("app.trasfer.accoutn.insufficientfunds.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.trasfer.accoutn.insufficientfunds.mesage", null, LocaleContextHolder.getLocale()));
		}
	}
	
	private void tranferRegistration(Date actualDate, TransferDTO transferDTO,BigDecimal amountUSD) {
		
		 Timestamp ts=new Timestamp(actualDate.getTime());  
		 
		 log.info("Timestamp {}",ts.toString());
		
		AccounttransfersPK accounttransfersPK = new AccounttransfersPK(ts.toString(),
				transferDTO.getRecipientAcount().getUsersidfinancialcompany(),
				transferDTO.getRecipientAcount().getIdacount(),
				transferDTO.getSourceAcount().getUsersidfinancialcompany(),
				transferDTO.getSourceAcount().getIdacount());
		
		Accounttransfers accounttransfers = new Accounttransfers(accounttransfersPK,
				amountUSD,
				transferDTO.getTransferDescripcion(), 
				transferDTO.getTransferSymbol(),
				transferDTO.getTransferAmount());
		
		accountsTransferRepository.save(accounttransfers);
	}
}
