package coop.home.bankaccount.dto.accounttransfer.consult;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferDetailDTO {

	private String transferDate;
	
	private AccountConsultDTO sourceAccount;
	
	private AccountConsultDTO recipientAccount;
	
	private String transferSymbol;
	
	private BigDecimal transferAmount;
	
	private String transferDescripcion;
}
