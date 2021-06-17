package coop.home.bankaccount.service;

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
import lombok.extern.slf4j.Slf4j;

public interface AccountsTransferConsultService {
	
	

	public TransferConsultDTO getTransfers(BigInteger idfinancialcompany, String loginuser,TokenBankAccountDTO tokenBankAccountDTO, 
			int pageNumber, int numberElements) ;
	
	
	
	
}
