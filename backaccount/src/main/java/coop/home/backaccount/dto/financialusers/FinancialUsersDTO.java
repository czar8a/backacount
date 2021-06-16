/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.home.backaccount.dto.financialusers;


import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinancialUsersDTO {
 
	private String iddocument;
    private String iddoctype;
    private BigInteger idfinancialcompany;
    private String loginuser;
    private String financialusername;
    private String financialuserlastname;
  
}
