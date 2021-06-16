package coop.home.backaccount.service;

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
import lombok.extern.slf4j.Slf4j;

public interface AccountsTransferConsultService {
	
	

	public TranseferConsultDTO getTransfers(BigInteger idfinancialcompany, String loginuser,TokenBackAcountDTO tokenBackAcountDTO, 
			int pageNumber, int numberElements) ;
	
	
	
	
}
