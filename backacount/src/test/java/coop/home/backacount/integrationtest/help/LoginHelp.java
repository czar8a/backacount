package coop.home.backacount.integrationtest.help;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import coop.home.backacount.dto.TokenBackAcountDTO;
import coop.home.backacount.dto.financialusers.FinancialUsersLoginDTO;
import coop.home.backacount.service.LoginUsuarioServicio;
import lombok.extern.slf4j.Slf4j;

@Service
@Scope("singleton")
@Slf4j
public class LoginHelp {

	@Autowired
	private LoginUsuarioServicio loginUsuarioServicio;
	
	private TokenBackAcountDTO user1;
	private TokenBackAcountDTO user2;
	
	public TokenBackAcountDTO getUser1() {
		
		if(user1==null) {
			user1= loginUsuarioServicio.logInUsuario(
				new FinancialUsersLoginDTO(BigInteger.valueOf(1),"owen","#$erwaf343"));
			
			log.info("login user 1");
		}
		
		return user1;
	}
	
	public TokenBackAcountDTO getUser2() {
		
		if(user2==null) {
			user2= loginUsuarioServicio.logInUsuario(
				new FinancialUsersLoginDTO(BigInteger.valueOf(1),"czar8a","12345$"));
			log.info("login user 2");
		}
		
		return user2;
	}
}
