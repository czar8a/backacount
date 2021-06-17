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
class LoginUsuarioServicioTest {
	
	@Autowired
	private LoginUsuarioServicio loginUsuarioServicio;
	
	@Autowired
	private LoginHelp loginHelp;

	@Test
	void shouldRenewToken_ReturnNewToken() {
		
		TokenBankAccountDTO tokenBankAccountRenew = loginUsuarioServicio.renovarToken(loginHelp.getUser1().getToken());
		
		assertNotNull(tokenBankAccountRenew.getToken());
	}
	
	@Test
	void shouldValidateUserPass_ReturnException() {
		
		assertThrows(UnAuthorizedException.class, () -> {
			loginUsuarioServicio.logInUsuario(new FinancialUsersLoginDTO(BigInteger.valueOf(1),"owen","123"));
		  });
	}
	
	@Test
	void shouldValidateUserLogin_ReturnException() {
		
		assertThrows(UnAuthorizedException.class, () -> {
			loginUsuarioServicio.logInUsuario(new FinancialUsersLoginDTO(BigInteger.valueOf(1),"123","#$erwaf343"));
		  });
	}

}
