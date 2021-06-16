package coop.home.backaccount.controller;

import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coop.home.backaccount.dto.financialusers.FinancialUsersDTO;
import coop.home.backaccount.service.FinancialUsersService;
import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin
@RequestMapping("/financialusers")
public class FinancialUsersController {
	
	@Autowired
	private FinancialUsersService financialUsersService;
	
	@GetMapping()
	@ApiOperation(value = "get Financial Users of a companie", notes = "Returns All Financial Users.")
	public ResponseEntity<List<FinancialUsersDTO>> getFinancialCompanies(@RequestHeader("Authorization") String token,
			@RequestParam(required = true,defaultValue = "1") BigInteger idfinancialcompany) {
		
		return ResponseEntity.status(HttpStatus.OK).body(financialUsersService.getFinancialUsers(idfinancialcompany));
	}
	
}
