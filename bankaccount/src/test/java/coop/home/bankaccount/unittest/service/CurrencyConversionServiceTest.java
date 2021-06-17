package coop.home.bankaccount.unittest.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import coop.home.bankaccount.dto.accounttransfer.RatesDTO;
import coop.home.bankaccount.service.ExchangeRatesService;
import coop.home.bankaccount.service.impl.CurrencyConversionServiceImpl;

//@TestPropertySource(locations  = "classpath:resources/application.properties")
//@PropertySource({"classpath:resources/application.properties"})
//@TestPropertySource(properties = { "exchangerates.defaultsymbol=USD" }) 
class CurrencyConversionServiceTest {
	
	@InjectMocks
	private CurrencyConversionServiceImpl currencyConversionService;
	
	@Mock
	private ExchangeRatesService exchangeRatesService;
	
	private RatesDTO ratesDTO;
	
	@BeforeEach
	public void setUpBefore() {
		
		MockitoAnnotations.initMocks(this);
		
		HashMap<String ,Double> rates= new HashMap<String, Double>();
		rates.put("USD", 1.219594);
		rates.put("BRL", 6.154069);
		rates.put("BAM", 1.962095);
		rates.put("COP", 4407.637098);
		
		ratesDTO= new RatesDTO(new Date(),"EUR",rates);
		
		ReflectionTestUtils.setField(currencyConversionService, "defaultSymbol", "USD");
		
		Mockito.when(exchangeRatesService.isValidSymbol(Mockito.anyString())).thenReturn(true);
		
		Mockito.when(exchangeRatesService.getRates()).thenReturn(ratesDTO);
	}

	
	@Test
	void shouldConverUSDValue_returnUSDValue() {
		BigDecimal conversion = currencyConversionService.getUSConversion("USD",BigDecimal.valueOf(1000));
		
		assertEquals(BigDecimal.valueOf(1000).compareTo(conversion),0);
	}
	
	@Test
	void shouldConverBRLValue_returnUSDValue() {
		BigDecimal conversion = currencyConversionService.getUSConversion("BRL",BigDecimal.valueOf(1000));
		
		assertEquals(BigDecimal.valueOf(133.2362374).compareTo(conversion),0);
	}
	
	@Test
	void shouldConverBAMValue_returnUSDValue() {
		BigDecimal conversion = currencyConversionService.getUSConversion("BAM",BigDecimal.valueOf(1000));
		
		assertEquals(BigDecimal.valueOf(417.8926087).compareTo(conversion),0);
	}
	
	@Test
	void shouldConverCOPValue_returnUSDValue() {
		BigDecimal conversion = currencyConversionService.getUSConversion("COP",BigDecimal.valueOf(1000));
		
		assertEquals(BigDecimal.valueOf(0.1860282460).compareTo(conversion),0);
	}

}
