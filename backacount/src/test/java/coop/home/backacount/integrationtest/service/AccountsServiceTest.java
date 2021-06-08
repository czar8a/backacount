package coop.home.backacount.integrationtest.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import coop.home.backacount.dto.TokenBackAcountDTO;
import coop.home.backacount.dto.accounts.AccountsDTO;
import coop.home.backacount.dto.financialusers.FinancialUsersLoginDTO;
import coop.home.backacount.excepcion.UnAuthorizedException;
import coop.home.backacount.integrationtest.help.LoginHelp;
import coop.home.backacount.service.AccountsService;
import coop.home.backacount.service.LoginUsuarioServicio;
import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
class AccountsServiceTest {
	
	@Autowired
	private AccountsService accountsService;
	
	@Autowired
	private LoginHelp loginHelp;

	@Test
	void shouldLoadAcount_ReturnAcountList() {
		
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
