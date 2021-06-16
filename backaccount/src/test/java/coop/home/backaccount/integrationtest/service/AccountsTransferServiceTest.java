package coop.home.backaccount.integrationtest.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import coop.home.backaccount.dto.accounts.AccountIDDTO;
import coop.home.backaccount.dto.acouttransfer.TransferDTO;
import coop.home.backaccount.dto.acouttransfer.TransferDoneDTO;
import coop.home.backaccount.dto.acouttransfer.consult.TranseferConsultDTO;
import coop.home.backaccount.dto.transaction.TransactionDTO;
import coop.home.backaccount.excepcion.BusinessException;
import coop.home.backaccount.excepcion.UnAuthorizedException;
import coop.home.backaccount.integrationtest.help.LoginHelp;
import coop.home.backaccount.service.AccountsTransferConsultService;
import coop.home.backaccount.service.AccountsTransferService;
import coop.home.backaccount.service.TransactionService;
import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
class AccountsTransferServiceTest {
	
	@Autowired
	private AccountsTransferService accountsTransferService;
	
	@Autowired
	private AccountsTransferConsultService accountsTransferConsultService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private LoginHelp loginHelp;
	
	private TransferDTO transferDTO;
	
	@BeforeEach
	public void setUpBefore() {
		transferDTO = new TransferDTO(new AccountIDDTO(BigInteger.valueOf(1),"12354352J543"), 
				new AccountIDDTO(BigInteger.valueOf(1),"74926A4045324543"), 
				"COP", BigDecimal.valueOf(1000000), "test tranfer");
	}
	
	

	@Test
	@Order(1) 
	void shouldMadeTransefer_ReturnTranseferConsultDTO () {	
		
		TransferDoneDTO transferDoneDTO = accountsTransferService.transfer(transferDTO,
				loginHelp.getUser1());
		
		assertNotNull(transferDoneDTO);
	}
	
	@Test
	@Order(2) 
	void shouldValidateAcountOwner_ReturnException() {
		
		assertThrows(BusinessException.class, () -> {
			accountsTransferService.transfer(transferDTO,
					loginHelp.getUser2());
		  });
	}
	
	@Test
	@Order(3) 
	void shouldValidateAcountExist_ReturnException() {
		
		transferDTO.setRecipientAcount(new AccountIDDTO(BigInteger.valueOf(1),"xxxxxx"));
		
		assertThrows(BusinessException.class, () -> {
			accountsTransferService.transfer(transferDTO,
					loginHelp.getUser1());
		  });
	}
	
	@Test
	@Order(4) 
	void shouldValidateSufficientfunds_ReturnException() {
		
		transferDTO.setTransferSymbol("USD");
		transferDTO.setTransferAmount(BigDecimal.valueOf(10000000));
		
		assertThrows(BusinessException.class, () -> {
			accountsTransferService.transfer(transferDTO,
					loginHelp.getUser1());
		  });
	}
	
	@Test
	@Order(5) 
	void shouldConsultTransefers_ReturnTranseferConsultDTO () {
		
		
		TranseferConsultDTO transeferConsultDTO = accountsTransferConsultService.getTransfers(BigInteger.valueOf(1), "owen",
				loginHelp.getUser1(),
				1, 10);
		
		assertNotNull(transeferConsultDTO);
	}
	
	@Test
	@Order(6) 
	void shouldValidateUser_ReturnException() {
			
		assertThrows(UnAuthorizedException.class, () -> {
			accountsTransferConsultService.getTransfers(BigInteger.valueOf(1), "owen",
					loginHelp.getUser2(),
					1, 10);
		  });
	}
	
	
	@Test
	@Order(7) 
	void shouldGetAcountTransactions_ReturnList() {
		
		List<TransactionDTO> transactios = transactionService.getAcountTransactions(
				transferDTO.getSourceAcount().getUsersidfinancialcompany(),
				transferDTO.getSourceAcount().getIdacount(), 
				loginHelp.getUser1());
			
		assertNotNull(transactios);
	}
	
	@Test
	@Order(8) 
	void shouldValidateAcountExists_ReturnExeption() {
			
		assertThrows(BusinessException.class, () -> {
			transactionService.getAcountTransactions(
					transferDTO.getSourceAcount().getUsersidfinancialcompany(),
					"XXXXXX", 
					loginHelp.getUser1());
		  });
	}
	
	
	@Test
	@Order(8) 
	void shouldValidateUserOwner_ReturnExeption() {
			
		assertThrows(UnAuthorizedException.class, () -> {
			transactionService.getAcountTransactions(
					transferDTO.getSourceAcount().getUsersidfinancialcompany(),
					transferDTO.getSourceAcount().getIdacount(), 
					loginHelp.getUser2());
		  });
	}
		
}
