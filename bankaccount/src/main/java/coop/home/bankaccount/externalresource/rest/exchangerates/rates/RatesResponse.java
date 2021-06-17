package coop.home.bankaccount.externalresource.rest.exchangerates.rates;

import lombok.Data;

@Data
class RatesResponse {
	public boolean success;
    public int timestamp;
    public String base;
    public String date;
    public Rates rates;
}
