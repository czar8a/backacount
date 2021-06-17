/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.home.bankaccount.dto.accounts;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountIDBalanceDTO extends AccountIDDTO {
	
	
	@Positive()
	private BigDecimal balance;
	
	public AccountIDBalanceDTO(BigInteger usersidfinancialcompany,String idaccount,BigDecimal balance) {
		super(usersidfinancialcompany,idaccount);
		
		this.balance= balance;
	}

	



}
