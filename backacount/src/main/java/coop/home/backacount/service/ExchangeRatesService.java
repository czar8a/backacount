package coop.home.backacount.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import coop.home.backacount.dto.acouttransfer.RatesDTO;
import coop.home.backacount.dto.acouttransfer.SymbolDTO;
import coop.home.backacount.dto.acouttransfer.SymbolsDTO;
import coop.home.backacount.externalresource.rest.ExchangeratesClient;
import coop.home.backacount.externalresource.rest.ExchangeratesSymbolsClient;
import coop.home.backacount.util.CustomDateFunctions;
import lombok.extern.slf4j.Slf4j;

public interface ExchangeRatesService {
	

	public SymbolsDTO getSymbols() ;
	
	public boolean isValidSymbol(String symbol) ;
	
	public RatesDTO getRates() ;
	
}
