package coop.home.bankaccount.integrationtest.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import coop.home.bankaccount.dto.TokenBankAccountDTO;
import coop.home.bankaccount.dto.accounts.AccountsDTO;
import coop.home.bankaccount.dto.financialusers.FinancialUsersLoginDTO;
import coop.home.bankaccount.excepcion.UnAuthorizedException;
import coop.home.bankaccount.integrationtest.help.LoginHelp;
import coop.home.bankaccount.service.AccountsService;
import coop.home.bankaccount.service.LoginUsuarioServicio;
import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
class AccountsServiceTest {
	
	@Autowired
	private AccountsService accountsService;
	
	@Autowired
	private LoginHelp loginHelp;

	@Test
	void shouldLoadAccount_ReturnAccountList() {
		
		List<AccountsDTO> accountsDTO = accountsService.getAccounts(BigInteger.valueOf(1), "owen",
				loginHelp.getUser1());
		
		assertNotNull(accountsDTO);
	}
	
	@Test
	void shouldValidateUser_ReturnException() {
		
		assertThrows(UnAuthorizedException.class, () -> {
			accountsService.getAccounts(BigInteger.valueOf(1), "owen",
					loginHelp.getUser2());
		  });
	}

}
