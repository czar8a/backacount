/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.home.backaccount.dto.accounts;

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
public class AccountIDDTO  {

	@Positive()
	@ApiModelProperty(notes = "Unique identifier of the Company.", example = "1", required = true, position = 0)
	private BigInteger usersidfinancialcompany;
	
	@NotBlank
	@ApiModelProperty(notes = "idacount", example = "74926A4045324543", required = true, position = 1)
	private String idacount;


}
