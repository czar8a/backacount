package coop.home.bankaccount.integrationtest.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import coop.home.bankaccount.dto.accounttransfer.RatesDTO;
import coop.home.bankaccount.dto.accounttransfer.SymbolDTO;
import coop.home.bankaccount.dto.accounttransfer.SymbolsDTO;
import coop.home.bankaccount.externalresource.rest.ExchangeratesSymbolsClient;
import coop.home.bankaccount.service.ExchangeRatesService;
import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
class ExchangeRatesServiceTest {
	
	@Autowired
	private ExchangeRatesService exchangeRatesServiceA;
	
	@Autowired
	private ExchangeRatesService exchangeRatesServiceB;

	@Test
	void shouldLoadRatesAsSingleton_bothInstancesWillBeTheSame() {
		exchangeRatesServiceA.getRates();
		
		assertEquals(exchangeRatesServiceA,exchangeRatesServiceB);
	}
	
	@Test
	void shouldLoadRates_RatesWereLoaded() {
		RatesDTO ratesDTO= exchangeRatesServiceA.getRates();
		
		assertTrue(ratesDTO.getRates().size()>0);
	}
	
	
	@Test
	void shouldLoadSymbols_SymbolsWereLoaded() {
		SymbolsDTO symbolsDTO= exchangeRatesServiceA.getSymbols();
		
		assertTrue(symbolsDTO.getSymbols().size()>0);
	}
	
	
	@Test
	void shouldEvaluateSymbol_returnTrue() {
		
		assertTrue(exchangeRatesServiceA.isValidSymbol("USD"));
	}
	
	@Test
	void shouldEvaluateSymbol_returnFalse() {
		
		assertFalse(exchangeRatesServiceA.isValidSymbol("XXX"));
	}

}
