package coop.home.backaccount.integrationtest.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import coop.home.backaccount.dto.FinancialCompanyDTO;
import coop.home.backaccount.dto.TokenBackAcountDTO;
import coop.home.backaccount.dto.accounts.AccountsDTO;
import coop.home.backaccount.dto.financialusers.FinancialUsersLoginDTO;
import coop.home.backaccount.excepcion.UnAuthorizedException;
import coop.home.backaccount.integrationtest.help.LoginHelp;
import coop.home.backaccount.service.AccountsService;
import coop.home.backaccount.service.FinancialCompanyService;
import coop.home.backaccount.service.LoginUsuarioServicio;
import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
class FinancialCompanyServiceTest {
	
	@Autowired
	private FinancialCompanyService financialCompanyService;

	@Test
	void shouldLoadFinancialCompany_ReturnFinancialCompanyList() {
			
		List<FinancialCompanyDTO> financialCompanies =  financialCompanyService.getFinancialCompanies();
		
		assertTrue(financialCompanies.size()>0);
	}
	
}
