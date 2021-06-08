package coop.home.backacount.externalresource.rest;

import java.util.List;

import coop.home.backacount.dto.acouttransfer.SymbolDTO;

public interface ExchangeratesSymbolsClient {

	public List<SymbolDTO> getSymbols();
}
