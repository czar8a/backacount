package coop.home.bankaccount.dto;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialCompanyDTO {
	
	private BigInteger idfinancialcompany;
	private String name;
	
}
