package coop.home.backacount.dto.acouttransfer.consult;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TranseferDetailDTO {

	private String transferDate;
	
	private AccountConsultDTO sourceAcount;
	
	private AccountConsultDTO recipientAcount;
	
	private String transferSymbol;
	
	private BigDecimal transferAmount;
	
	private String transferDescripcion;
}
