package coop.home.backacount.externalresource.rest;

import coop.home.backacount.dto.acouttransfer.RatesDTO;

public interface ExchangeratesClient {

	public RatesDTO getRates();
}
