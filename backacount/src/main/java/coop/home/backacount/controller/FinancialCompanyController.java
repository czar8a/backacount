package coop.home.backacount.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import coop.home.backacount.dto.FinancialCompanyDTO;
import coop.home.backacount.service.FinancialCompanyService;
import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin
@RequestMapping("/financialcompany")
public class FinancialCompanyController {
	
	@Autowired
	private FinancialCompanyService financialCompanyService;
	
	@GetMapping()
	@ApiOperation(value = "get Financial Companies", notes = "Returns All Financial Companies.")
	public ResponseEntity<List<FinancialCompanyDTO>> getFinancialCompanies(@RequestHeader("Authorization") String token) {

		return ResponseEntity.status(HttpStatus.OK).body(financialCompanyService.getFinancialCompanies());
	}
	
}
