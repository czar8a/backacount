package coop.home.backaccount.service.impl;

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

import coop.home.backaccount.dto.TokenBackAcountDTO;
import coop.home.backaccount.dto.acouttransfer.consult.AccountConsultDTO;
import coop.home.backaccount.dto.acouttransfer.consult.FinancialUsersAcauntDTO;
import coop.home.backaccount.dto.acouttransfer.consult.TranseferConsultDTO;
import coop.home.backaccount.dto.acouttransfer.consult.TranseferDetailDTO;
import coop.home.backaccount.excepcion.UnAuthorizedException;
import coop.home.backaccount.repository.IAccountsRepository;
import coop.home.backaccount.repository.IAccountsTransferRepository;
import coop.home.backaccount.repository.entity.Accounts;
import coop.home.backaccount.repository.entity.Accounttransfers;
import coop.home.backaccount.repository.specification.CustomSpecification;
import coop.home.backaccount.service.AccountsTransferConsultService;
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
	

	public TranseferConsultDTO getTransfers(BigInteger idfinancialcompany, String loginuser,TokenBackAcountDTO tokenBackAcountDTO, 
			int pageNumber, int numberElements) {
		
		if(!tokenBackAcountDTO.getLoginUser().equals(loginuser)) {
			throw new UnAuthorizedException(mensajes.getMessage("app.security.acountowner.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.security.acountowner.mesage", null, LocaleContextHolder.getLocale()));
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
	
	private TranseferConsultDTO map(List<Accounttransfers> result, long totalRecords) {
		
		List<TranseferDetailDTO> transeferDetails = new ArrayList<>();

		for (Accounttransfers accounttransfer : result) {
			
			TranseferDetailDTO transeferDetail = new TranseferDetailDTO(
					accounttransfer.getAccounttransfersPK().getTransferdate(), 
					new AccountConsultDTO(
							accounttransfer.getAccounttransfersPK().getSourceaccountidcompany(),
							accounttransfer.getAccounttransfersPK().getSourceaccountidacount(), 
							new FinancialUsersAcauntDTO(
									accounttransfer.getSourceAccount().getFinancialusers().getIddocument(),
									accounttransfer.getSourceAccount().getFinancialusers().getIddoctype(),
									accounttransfer.getSourceAccount().getFinancialusers().getFinancialusersPK().getLoginuser(),
									accounttransfer.getSourceAccount().getFinancialusers().getFinancialusername(),
									accounttransfer.getSourceAccount().getFinancialusers().getFinancialuserlastname())),
					new AccountConsultDTO(
							accounttransfer.getAccounttransfersPK().getRecipientaccountidcompany(),
							accounttransfer.getAccounttransfersPK().getRecipientaccountidacount(), 
							new FinancialUsersAcauntDTO(
									accounttransfer.getRecipentAccounts().getFinancialusers().getIddocument(),
									accounttransfer.getRecipentAccounts().getFinancialusers().getIddoctype(),
									accounttransfer.getRecipentAccounts().getFinancialusers().getFinancialusersPK().getLoginuser(),
									accounttransfer.getRecipentAccounts().getFinancialusers().getFinancialusername(),
									accounttransfer.getRecipentAccounts().getFinancialusers().getFinancialuserlastname())), 
					defaultSymbol, 
					accounttransfer.getAmount(), 
					accounttransfer.getDescription());
			
			
			transeferDetails.add(transeferDetail);
			
		}
		
		return new TranseferConsultDTO(totalRecords,transeferDetails);
	}
	
	
}
