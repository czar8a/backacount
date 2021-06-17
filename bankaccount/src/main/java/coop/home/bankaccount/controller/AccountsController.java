package coop.home.bankaccount.controller;

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

import coop.home.bankaccount.dto.TokenBankAccountDTO;
import coop.home.bankaccount.dto.accounts.AccountsDTO;
import coop.home.bankaccount.dto.transaction.TransactionDTO;
import coop.home.bankaccount.service.AccountsService;
import coop.home.bankaccount.service.TransactionService;
import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin
@RequestMapping("/accounts")
public class AccountsController {
	
	@Autowired
	private AccountsService accountsService;
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping()
	@ApiOperation(value = "get User's Accounts", notes = "Returns All get User's Accounts.")
	public ResponseEntity<List<AccountsDTO>> getAccounts(@RequestHeader("Authorization") String token,
			@RequestParam(required = true) BigInteger idfinancialcompany,
			@RequestParam(required = true) String loginuser) {

		return ResponseEntity.status(HttpStatus.OK).body(accountsService.getAccounts(idfinancialcompany, loginuser,new TokenBankAccountDTO(token)));
	}
	
	
	@GetMapping("/transactions")
	@ApiOperation(value = "get User's Accounts transactions", notes = "Returns All get User's Accounts transactions.")
	public ResponseEntity<List<TransactionDTO>> getAccountsTransactions(@RequestHeader("Authorization") String token,
			@RequestParam(required = true) BigInteger idfinancialcompany,
			@RequestParam(required = true) String idaccount) {

		return ResponseEntity.status(HttpStatus.OK).body(transactionService.getAccountTransactions(idfinancialcompany, idaccount,new TokenBankAccountDTO(token)));
	}
	
}
