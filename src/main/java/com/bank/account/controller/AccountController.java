package com.bank.account.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.dto.AccountDto;
import com.bank.account.dto.AccountResponseDto;
import com.bank.account.dto.FormFieldError;
import com.bank.account.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/account")
public class AccountController {

	AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@Operation(description =  "Add new bank account details", tags = "Add Account")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success|OK"),
			@ApiResponse(responseCode = "400", description = "UnAuthorized"),
			@ApiResponse(responseCode = "404", description = "Not Found")
	})
	@PostMapping("/add-account")
	public ResponseEntity<?> saveAccount(@Valid @RequestBody AccountDto account, BindingResult result) {
		if (result.hasErrors()) {
			List<FormFieldError> formFieldErrors = result.getFieldErrors().stream()
					.map(err -> new FormFieldError(err.getField(), err.getDefaultMessage())).toList();
			account.setFormErrors(formFieldErrors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(account);
		}
		AccountResponseDto saveAccount = accountService.saveAccount(account);
		return new ResponseEntity<AccountResponseDto>(saveAccount, HttpStatus.CREATED);
	}

	@Operation(description =  "Get bank account details by UUID", tags = "Get Account")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success|OK"),
			@ApiResponse(responseCode = "400", description = "UnAuthorized"),
			@ApiResponse(responseCode = "404", description = "Not Found")
	})
	@GetMapping("/get-account/{accountId}")
	public ResponseEntity<?> getAccountDetails(@PathVariable String accountId) {
		AccountDto account = accountService.getAccountDetails(accountId);
		if (account != null) {
			return new ResponseEntity<AccountDto>(account, HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountDto>(HttpStatus.NO_CONTENT);
		}

	}
	
	@Operation(description =  "Update bank account details", tags = "Update Account")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success|OK"),
			@ApiResponse(responseCode = "400", description = "UnAuthorized"),
			@ApiResponse(responseCode = "404", description = "Not Found")
	})
	@PutMapping("/update-account/{accountId}")
	public ResponseEntity<?> updateAccount(@Valid @RequestBody AccountDto account, BindingResult result,  
			@PathVariable String accountId) {
		if (result.hasErrors()) {
			List<FormFieldError> formFieldErrors = result.getFieldErrors().stream()
					.map(err -> new FormFieldError(err.getField(), err.getDefaultMessage())).toList();
			account.setFormErrors(formFieldErrors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(account);
		}
		AccountResponseDto saveAccount = accountService.updateAccount(account, accountId);
		return new ResponseEntity<AccountResponseDto>(saveAccount, HttpStatus.CREATED);
	}

	@Operation(description =  "Delete a bank account", tags = "Delete Account")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success|OK"),
			@ApiResponse(responseCode = "400", description = "UnAuthorized"),
			@ApiResponse(responseCode = "404", description = "Not Found")
	})
	@DeleteMapping("/delete-account/{accountId}")
	public ResponseEntity<?> deleteAccount(@PathVariable String accountId) {
		return ResponseEntity.status(HttpStatus.OK).body(accountService.deleteAccount(accountId));
	}
}
