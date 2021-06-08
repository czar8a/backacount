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
class LoginUsuarioServicioTest {
	
	@Autowired
	private LoginUsuarioServicio loginUsuarioServicio;
	
	@Autowired
	private LoginHelp loginHelp;

	@Test
	void shouldRenewToken_ReturnNewToken() {
		
		TokenBackAcountDTO tokenBackAcountRenew = loginUsuarioServicio.renovarToken(loginHelp.getUser1().getToken());
		
		assertNotNull(tokenBackAcountRenew.getToken());
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
