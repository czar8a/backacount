package coop.home.bankaccount.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import coop.home.bankaccount.dto.accounttransfer.RatesDTO;
import coop.home.bankaccount.dto.accounttransfer.SymbolDTO;
import coop.home.bankaccount.dto.accounttransfer.SymbolsDTO;
import coop.home.bankaccount.externalresource.rest.ExchangeratesClient;
import coop.home.bankaccount.externalresource.rest.ExchangeratesSymbolsClient;
import coop.home.bankaccount.util.CustomDateFunctions;
import lombok.extern.slf4j.Slf4j;

public interface ExchangeRatesService {
	

	public SymbolsDTO getSymbols() ;
	
	public boolean isValidSymbol(String symbol) ;
	
	public RatesDTO getRates() ;
	
}
