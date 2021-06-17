package coop.home.bankaccount.dto.accounttransfer.consult;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferConsultDTO {

	private long totalRecords;
	
	private List<TransferDetailDTO> transfers;
}
