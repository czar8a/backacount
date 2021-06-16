package coop.home.backaccount.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import coop.home.backaccount.dto.acouttransfer.RatesDTO;
import coop.home.backaccount.dto.acouttransfer.SymbolDTO;
import coop.home.backaccount.dto.acouttransfer.SymbolsDTO;
import coop.home.backaccount.externalresource.rest.ExchangeratesClient;
import coop.home.backaccount.externalresource.rest.ExchangeratesSymbolsClient;
import coop.home.backaccount.util.CustomDateFunctions;
import lombok.extern.slf4j.Slf4j;

public interface ExchangeRatesService {
	

	public SymbolsDTO getSymbols() ;
	
	public boolean isValidSymbol(String symbol) ;
	
	public RatesDTO getRates() ;
	
}
