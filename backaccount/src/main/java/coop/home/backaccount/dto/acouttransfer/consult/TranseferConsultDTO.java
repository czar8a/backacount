package coop.home.backaccount.dto.acouttransfer.consult;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TranseferConsultDTO {

	private long totalRecords;
	
	private List<TranseferDetailDTO> transfers;
}
