/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.home.backacount.dto.acouttransfer.consult;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinancialUsersAcauntDTO {
 
	private String iddocument;
    private String iddoctype;
    private String loginuser;
    private String financialusername;
    private String financialuserlastname;
  
}
