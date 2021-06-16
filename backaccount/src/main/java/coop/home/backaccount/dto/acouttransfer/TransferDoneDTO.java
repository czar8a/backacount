package coop.home.backaccount.dto.acouttransfer;

import java.math.BigDecimal;
import java.util.Date;

import coop.home.backaccount.dto.accounts.AccountIDBalanceDTO;
import coop.home.backaccount.util.CustomDateFunctions;
import lombok.Data;


@Data
public class TransferDoneDTO extends TransferBalanceDTO {
	
	private String transferDate;
	private BigDecimal finalAmount;
	private String finalSymbol;
	
	public TransferDoneDTO(AccountIDBalanceDTO sourceAcount, AccountIDBalanceDTO recipientAcount, BigDecimal transferAmount,
			String transferSymbol, String transferDescripcion,Date transferDateDate, BigDecimal finalAmount, String finalSymbol) {
		
		super(sourceAcount, recipientAcount, transferSymbol, transferAmount, transferDescripcion);

		this.setDate(transferDateDate);
		this.finalAmount = finalAmount;
		this.finalSymbol = finalSymbol;
	}
	
	private void setDate(Date actualDate) {
		this.transferDate = CustomDateFunctions.getTimeStampWithMinutes(actualDate);
	}
	
	
	

}
