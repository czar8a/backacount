package coop.home.bankaccount.dto.financialusers;

import java.math.BigInteger;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor 
@NoArgsConstructor
public class FinancialUsersLoginDTO {
	
	@Positive()
	@ApiModelProperty(notes = "Unique identifier of the Company.", example = "1", required = true, position = 0)
	private BigInteger idfinancialcompany;
	

	@NotBlank
	@ApiModelProperty(notes = "Unique identifier of the user.", example = "owen", required = true, position = 1)
	private String loginuser;
	
	@NotBlank
	@ApiModelProperty(notes = "user password.", example = "#$erwaf343", required = true, position = 2)
	private String pass;
}
