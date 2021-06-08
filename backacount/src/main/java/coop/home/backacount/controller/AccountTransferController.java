package coop.home.backacount.controller;

import java.math.BigInteger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coop.home.backacount.dto.TokenBackAcountDTO;
import coop.home.backacount.dto.acouttransfer.SymbolsDTO;
import coop.home.backacount.dto.acouttransfer.TransferDTO;
import coop.home.backacount.dto.acouttransfer.TransferDoneDTO;
import coop.home.backacount.dto.acouttransfer.consult.TranseferConsultDTO;
import coop.home.backacount.service.AccountsTransferConsultService;
import coop.home.backacount.service.AccountsTransferService;
import coop.home.backacount.service.ExchangeRatesService;
import io.swagger.annotations.ApiOperation;



@RestController
@CrossOrigin
@RequestMapping("/transfers")
public class AccountTransferController {
	
	@Autowired
	private ExchangeRatesService symbolsService;
	
	@Autowired
	private AccountsTransferService accountsTransferService;
	
	@Autowired
	private AccountsTransferConsultService accountsTransferConsultService;
	
	@GetMapping("/symbols")
	@ApiOperation(value = "get Available currency transfer symbols", notes = "Returns All Available currency transfer symbols.")
	public ResponseEntity<SymbolsDTO> getSymbols(@RequestHeader("Authorization") String token) {

		return ResponseEntity.status(HttpStatus.OK).body(symbolsService.getSymbols());
	}
	
	@PostMapping()
	@ApiOperation(value = "transfer acount", notes = "Returns transfer acount info.")
	public ResponseEntity<TransferDoneDTO> transfer(@RequestHeader("Authorization") String token,
			@Valid @RequestBody TransferDTO transferDTO) {

		return ResponseEntity.status(HttpStatus.CREATED).body(accountsTransferService.transfer(transferDTO, new TokenBackAcountDTO(token)));
	}
	
	
	@GetMapping("financialuser")
	@ApiOperation(value = "get User Transfers", notes = "Returns last month User Transfers.")
	public ResponseEntity<TranseferConsultDTO> getTransfers(@RequestHeader("Authorization") String token,
			@RequestParam(required = true, defaultValue = "1") BigInteger idfinancialcompany,
			@RequestParam(required = true) String loginuser,
			@RequestParam(defaultValue = "1", required = false) int pageNumber,
			@RequestParam(defaultValue = "10", required = false) int numberElements) {

		return ResponseEntity.status(HttpStatus.OK).body(accountsTransferConsultService.getTransfers(
				idfinancialcompany, loginuser, new TokenBackAcountDTO(token), pageNumber, numberElements));
	}
	
}
