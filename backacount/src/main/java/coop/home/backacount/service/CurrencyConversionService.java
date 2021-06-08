package coop.home.backacount.service;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;


import coop.home.backacount.excepcion.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@PropertySource("classpath:application.properties")
public class CurrencyConversionService {
	
	@Autowired
	private ExchangeRatesService exchangeRatesService;
	
	@Autowired
	private MessageSource mensajes;
	
	@Value("${exchangerates.defaultsymbol}")
	private String defaultSymbol;

	public BigDecimal getUSConvertion(String symbol, BigDecimal amount) {
		
		if(exchangeRatesService.isValidSymbol(symbol)) {
			if(symbol.equals(defaultSymbol))
				return amount;
			else {
				
				// Set precision to 10
		        MathContext mc
		            = new MathContext(10);
				
				double conversionRateToEUR = exchangeRatesService.getRates().getRates().get(symbol);
				BigDecimal amountEUR = amount.divide(BigDecimal.valueOf(conversionRateToEUR),mc);
				
				double conversionRateToUSD = exchangeRatesService.getRates().getRates().get(defaultSymbol);
				BigDecimal amountUSD = amountEUR.divide(BigDecimal.valueOf(conversionRateToUSD),mc);
				
				return amountUSD;
			}
		}
		else {
			throw new BusinessException(mensajes.getMessage("app.currency.convertion.novalidsymbol.code", null, LocaleContextHolder.getLocale()),
					mensajes.getMessage("app.currency.convertion.novalidsymbol.mesage", null, LocaleContextHolder.getLocale()));
		}
	}
}
