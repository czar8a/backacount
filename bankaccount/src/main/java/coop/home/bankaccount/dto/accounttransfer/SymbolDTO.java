package coop.home.bankaccount.dto.accounttransfer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SymbolDTO {
	private String symbol;
	private String name;
}
