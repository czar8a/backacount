package coop.home.backacount.service;

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
import lombok.extern.slf4j.Slf4j;

public interface AccountsTransferService {
	


	public TransferDoneDTO transfer(TransferDTO transferDTO, TokenBackAcountDTO tokenBackAcount) ;
	
	
}
