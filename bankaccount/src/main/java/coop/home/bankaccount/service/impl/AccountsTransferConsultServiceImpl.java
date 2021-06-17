package coop.home.bankaccount.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.home.bankaccount.dto.TokenBankAccountDTO;
import coop.home.bankaccount.dto.accounttransfer.consult.AccountConsultDTO;
import coop.home.bankaccount.dto.accounttransfer.consult.FinancialUsersAcauntDTO;
import coop.home.bankaccount.dto.accounttransfer.consult.TransferConsultDTO;
import coop.home.bankaccount.dto.accounttransfer.consult.TransferDetailDTO;
import coop.home.bankaccount.excepcion.UnAuthorizedException;
import coop.home.bankaccount.repository.IAccountsRepository;
import coop.home.bankaccount.repository.IAccountsTransferRepository;
import coop.home.bankaccount.repository.entity.Accounts;
import coop.home.bankaccount.repository.entity.Accounttransfers;
import coop.home.bankaccount.repository.specification.CustomSpecification;
import coop.home.bankaccount.service.AccountsTransferConsultService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
@PropertySource("classpath:application.properties")
class AccountsTransferConsultServiceImpl implements AccountsTransferConsultService {
	

	@Autowired
	private IAccountsTransferRepository accountsTransferRepository;
	
	@Autowired
	private IAccountsRepository accountsRepository;

	@Autowired
	private MessageSource mensajes;
	
	@Value("${exchangerates.defaultsymbol}")
	private String defaultSymbol;
	

	public TransferConsultDTO getTransfers(BigInteger idfinancialcompany, String loginuser,TokenBankAccountDTO tokenBankAccountDTO, 
			int pageNumber, int numberElements) {
		
		if(!tokenBankAccountDTO.getLoginUser().equals(loginuser)) {
			throw new UnAuthorizedException(mensajes.getMessage("app.security.accountowner.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.security.accountowner.message", null, LocaleContextHolder.getLocale()));
		}

		Sort sort = Sort.by(Order.desc("accounttransfersPK_transferdate"));
		Pageable pageable = PageRequest.of(pageNumber-1, numberElements,sort);
			
		Page<Accounttransfers> pagedResult = accountsTransferRepository.findAll(CustomSpecification.filterByUserAccounts(idfinancialcompany,loginuser) ,pageable);

		List<Accounttransfers> result = new ArrayList<>();

		if (pagedResult.hasContent()) {
			result.addAll(pagedResult.getContent());
		}
		
		return map(result, pagedResult.getTotalElements());
	}
	
	private TransferConsultDTO map(List<Accounttransfers> result, long totalRecords) {
		
		List<TransferDetailDTO> transferDetails = new ArrayList<>();

		for (Accounttransfers accounttransfer : result) {
			
			TransferDetailDTO transferDetail = new TransferDetailDTO(
					accounttransfer.getAccounttransfersPK().getTransferdate(), 
					new AccountConsultDTO(
							accounttransfer.getAccounttransfersPK().getSourceaccountidcompany(),
							accounttransfer.getAccounttransfersPK().getSourceaccountidaccount(), 
							new FinancialUsersAcauntDTO(
									accounttransfer.getSourceAccount().getFinancialusers().getIddocument(),
									accounttransfer.getSourceAccount().getFinancialusers().getIddoctype(),
									accounttransfer.getSourceAccount().getFinancialusers().getFinancialusersPK().getLoginuser(),
									accounttransfer.getSourceAccount().getFinancialusers().getFinancialusername(),
									accounttransfer.getSourceAccount().getFinancialusers().getFinancialuserlastname())),
					new AccountConsultDTO(
							accounttransfer.getAccounttransfersPK().getRecipientaccountidcompany(),
							accounttransfer.getAccounttransfersPK().getRecipientaccountidaccount(), 
							new FinancialUsersAcauntDTO(
									accounttransfer.getRecipentAccounts().getFinancialusers().getIddocument(),
									accounttransfer.getRecipentAccounts().getFinancialusers().getIddoctype(),
									accounttransfer.getRecipentAccounts().getFinancialusers().getFinancialusersPK().getLoginuser(),
									accounttransfer.getRecipentAccounts().getFinancialusers().getFinancialusername(),
									accounttransfer.getRecipentAccounts().getFinancialusers().getFinancialuserlastname())), 
					defaultSymbol, 
					accounttransfer.getAmount(), 
					accounttransfer.getDescription());
			
			
			transferDetails.add(transferDetail);
			
		}
		
		return new TransferConsultDTO(totalRecords,transferDetails);
	}
	
	
}
