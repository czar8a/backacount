package coop.home.backacount.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.home.backacount.dto.TokenBackAcountDTO;
import coop.home.backacount.dto.financialusers.FinancialUsersLoginDTO;
import coop.home.backacount.service.LoginUsuarioServicio;
import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin
@RequestMapping("/security")
public class LoginController {
	
	@Autowired
	private LoginUsuarioServicio userService;
	
	@PostMapping("/login")
	@ApiOperation(value = "userLogin", notes = "Returns the user data for interaction with the application.")
	public ResponseEntity<TokenBackAcountDTO> logInUser(@Valid @RequestBody FinancialUsersLoginDTO loginRequestDTO) {

		return ResponseEntity.status(HttpStatus.OK).body(userService.logInUsuario(loginRequestDTO));
	}
	
	
	@PostMapping("/renewToken")
	@ApiOperation(value = "renewToken", notes = "Returns new user data for interaction with the application.")
	public ResponseEntity<TokenBackAcountDTO> renovarToken(@RequestHeader("Authorization") String token) {

		return ResponseEntity.status(HttpStatus.OK).body(userService.renovarToken(token));
	}

}
