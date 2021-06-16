package coop.home.backaccount.externalresource.rest.exchangerates.symbols;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
class SymbolsResponse{
	
	@JsonProperty("success") 
    public boolean success;
	@JsonProperty("symbols") 
    public Symbols symbols;
}




