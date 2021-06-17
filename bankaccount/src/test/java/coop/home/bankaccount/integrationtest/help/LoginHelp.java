package coop.home.bankaccount.integrationtest.help;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import coop.home.bankaccount.dto.TokenBankAccountDTO;
import coop.home.bankaccount.dto.financialusers.FinancialUsersLoginDTO;
import coop.home.bankaccount.service.LoginUsuarioServicio;
import lombok.extern.slf4j.Slf4j;

@Service
@Scope("singleton")
@Slf4j
public class LoginHelp {

	@Autowired
	private LoginUsuarioServicio loginUsuarioServicio;
	
	private TokenBankAccountDTO user1;
	private TokenBankAccountDTO user2;
	
	public TokenBankAccountDTO getUser1() {
		
		if(user1==null) {
			user1= loginUsuarioServicio.logInUsuario(
				new FinancialUsersLoginDTO(BigInteger.valueOf(1),"owen","#$erwaf343"));
			
			log.info("login user 1");
		}
		
		return user1;
	}
	
	public TokenBankAccountDTO getUser2() {
		
		if(user2==null) {
			user2= loginUsuarioServicio.logInUsuario(
				new FinancialUsersLoginDTO(BigInteger.valueOf(1),"czar8a","12345$"));
			log.info("login user 2");
		}
		
		return user2;
	}
}
