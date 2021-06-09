package coop.home.backacount.service;

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

import coop.home.backacount.dto.TokenBackAcountDTO;
import coop.home.backacount.dto.acouttransfer.consult.AccountConsultDTO;
import coop.home.backacount.dto.acouttransfer.consult.FinancialUsersAcauntDTO;
import coop.home.backacount.dto.acouttransfer.consult.TranseferConsultDTO;
import coop.home.backacount.dto.acouttransfer.consult.TranseferDetailDTO;
import coop.home.backacount.excepcion.UnAuthorizedException;
import coop.home.backacount.repository.IAccountsRepository;
import coop.home.backacount.repository.IAccountsTransferRepository;
import coop.home.backacount.repository.entity.Accounts;
import coop.home.backacount.repository.entity.Accounttransfers;
import coop.home.backacount.repository.specification.CustomSpecification;
import lombok.extern.slf4j.Slf4j;

public interface AccountsTransferConsultService {
	
	

	public TranseferConsultDTO getTransfers(BigInteger idfinancialcompany, String loginuser,TokenBackAcountDTO tokenBackAcountDTO, 
			int pageNumber, int numberElements) ;
	
	
	
	
}
