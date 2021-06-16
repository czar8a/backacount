package coop.home.backaccount.externalresource.rest;

import java.util.List;

import coop.home.backaccount.dto.acouttransfer.SymbolDTO;

public interface ExchangeratesSymbolsClient {

	public List<SymbolDTO> getSymbols();
}
