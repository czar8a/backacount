package coop.home.bankaccount.externalresource.rest;

import coop.home.bankaccount.dto.accounttransfer.RatesDTO;

public interface ExchangeratesClient {

	public RatesDTO getRates();
}
