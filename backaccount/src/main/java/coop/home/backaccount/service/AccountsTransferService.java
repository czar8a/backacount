package coop.home.backaccount.service;

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

import coop.home.backaccount.dto.TokenBackAcountDTO;
import coop.home.backaccount.dto.accounts.AccountIDBalanceDTO;
import coop.home.backaccount.dto.acouttransfer.TransferDTO;
import coop.home.backaccount.dto.acouttransfer.TransferDoneDTO;
import coop.home.backaccount.excepcion.BusinessException;
import coop.home.backaccount.repository.IAccountsRepository;
import coop.home.backaccount.repository.IAccountsTransferRepository;
import coop.home.backaccount.repository.entity.Accounts;
import coop.home.backaccount.repository.entity.AccountsPK;
import coop.home.backaccount.repository.entity.Accounttransfers;
import coop.home.backaccount.repository.entity.AccounttransfersPK;
import lombok.extern.slf4j.Slf4j;

public interface AccountsTransferService {
	


	public TransferDoneDTO transfer(TransferDTO transferDTO, TokenBackAcountDTO tokenBackAcount) ;
	
	
}
