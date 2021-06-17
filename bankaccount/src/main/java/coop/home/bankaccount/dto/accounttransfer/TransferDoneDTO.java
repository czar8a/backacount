package coop.home.bankaccount.dto.accounttransfer;

import java.math.BigDecimal;
import java.util.Date;

import coop.home.bankaccount.dto.accounts.AccountIDBalanceDTO;
import coop.home.bankaccount.util.CustomDateFunctions;
import lombok.Data;


@Data
public class TransferDoneDTO extends TransferBalanceDTO {
	
	private String transferDate;
	private BigDecimal finalAmount;
	private String finalSymbol;
	
	public TransferDoneDTO(AccountIDBalanceDTO sourceAccount, AccountIDBalanceDTO recipientAccount, BigDecimal transferAmount,
			String transferSymbol, String transferDescripcion,Date transferDateDate, BigDecimal finalAmount, String finalSymbol) {
		
		super(sourceAccount, recipientAccount, transferSymbol, transferAmount, transferDescripcion);

		this.setDate(transferDateDate);
		this.finalAmount = finalAmount;
		this.finalSymbol = finalSymbol;
	}
	
	private void setDate(Date actualDate) {
		this.transferDate = CustomDateFunctions.getTimeStampWithMinutes(actualDate);
	}
	
	
	

}
