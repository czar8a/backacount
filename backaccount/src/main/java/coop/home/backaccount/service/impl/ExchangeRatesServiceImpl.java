package coop.home.backaccount.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import coop.home.backaccount.dto.acouttransfer.RatesDTO;
import coop.home.backaccount.dto.acouttransfer.SymbolDTO;
import coop.home.backaccount.dto.acouttransfer.SymbolsDTO;
import coop.home.backaccount.externalresource.rest.ExchangeratesClient;
import coop.home.backaccount.externalresource.rest.ExchangeratesSymbolsClient;
import coop.home.backaccount.service.ExchangeRatesService;
import coop.home.backaccount.util.CustomDateFunctions;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Scope("singleton")
class ExchangeRatesServiceImpl implements ExchangeRatesService {
	
	private SymbolsDTO symbolsDTO;
	private RatesDTO ratesDTO;

	@Autowired
	private ExchangeratesSymbolsClient exchangeratesClient;
	
	@Autowired
	private ExchangeratesClient exchangeratesSymbolsClient;

	public SymbolsDTO getSymbols() {
		
		if(symbolsDTO==null) {
			symbolsDTO = new SymbolsDTO(exchangeratesClient.getSymbols());
			log.info("Loading symbols");
		}
		
		return symbolsDTO;
	}
	
	public boolean isValidSymbol(String symbol) {
		
		for(SymbolDTO S: this.getSymbols().getSymbols()) {
			if(S.getSymbol().equals(symbol))
				return true;
		}
		
		return false;
	}
	
	public RatesDTO getRates() {
		
		if(ratesDTO==null || !this.isToday(ratesDTO.getDate())) {//if is null or data is not from today then load from service
			ratesDTO = exchangeratesSymbolsClient.getRates();
			log.info("Loading rates");
		}
		
		return ratesDTO;
	}
	
	private boolean isToday(Date date) {
		
		return CustomDateFunctions.isTodayWithOutMinutes(date); 
	}
}
