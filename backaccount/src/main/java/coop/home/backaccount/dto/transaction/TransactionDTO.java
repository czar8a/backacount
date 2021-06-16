package coop.home.backaccount.dto.transaction;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDTO {

	private BigInteger idfinancialcompany;
	
	private String idacount;
	
	private String transactionname;
	
	private String transactiondate;
	
	private BigDecimal amount;
}
