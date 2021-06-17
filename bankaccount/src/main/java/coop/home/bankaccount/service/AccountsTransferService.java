package coop.home.bankaccount.service;

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
import lombok.extern.slf4j.Slf4j;

public interface AccountsTransferService {
	


	public TransferDoneDTO transfer(TransferDTO transferDTO, TokenBankAccountDTO tokenBankAccount) ;
	
	
}
