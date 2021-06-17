package coop.home.bankaccount.integrationtest.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import coop.home.bankaccount.dto.FinancialCompanyDTO;
import coop.home.bankaccount.dto.TokenBankAccountDTO;
import coop.home.bankaccount.dto.accounts.AccountsDTO;
import coop.home.bankaccount.dto.financialusers.FinancialUsersDTO;
import coop.home.bankaccount.dto.financialusers.FinancialUsersLoginDTO;
import coop.home.bankaccount.excepcion.UnAuthorizedException;
import coop.home.bankaccount.integrationtest.help.LoginHelp;
import coop.home.bankaccount.service.AccountsService;
import coop.home.bankaccount.service.FinancialCompanyService;
import coop.home.bankaccount.service.FinancialUsersService;
import coop.home.bankaccount.service.LoginUsuarioServicio;
import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
class FinancialUsersServiceTest {
	
	@Autowired
	private FinancialUsersService financialUsersService;

	@Test
	void shouldLoadFinancialCompany_ReturnFinancialCompanyList() {
			
		List<FinancialUsersDTO> financialUsers =  financialUsersService.getFinancialUsers(BigInteger.valueOf(1));
		
		assertTrue(financialUsers.size()>0);
	}
	
}
