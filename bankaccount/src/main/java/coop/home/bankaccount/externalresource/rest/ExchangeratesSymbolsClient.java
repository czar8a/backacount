package coop.home.bankaccount.externalresource.rest;

import java.util.List;

import coop.home.bankaccount.dto.accounttransfer.SymbolDTO;

public interface ExchangeratesSymbolsClient {

	public List<SymbolDTO> getSymbols();
}
