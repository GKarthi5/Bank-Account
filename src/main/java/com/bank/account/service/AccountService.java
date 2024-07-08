package com.bank.account.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.account.dto.AccountDto;
import com.bank.account.dto.AccountResponseDto;
import com.bank.account.model.Account;
import com.bank.account.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	public AccountResponseDto saveAccount(AccountDto accountDto) {
		Account account = accountDto.toEntity();
		return new AccountResponseDto ("CREATED",new AccountDto(accountRepository.save(account)));
	}
	
	public AccountDto getAccountDetails(String uuid) {
		Optional<Account> account = accountRepository.findById(uuid);
		if (account.isPresent()) {
			return new AccountDto(account.get());
		}
		return null;
	}
	
	public AccountResponseDto updateAccount(AccountDto accountDto, String uuid ) {
		Optional<Account> account = accountRepository.findById(uuid);
		if (account.isPresent()) {
			Account updatedAccount = accountDto.toEntity();
			updatedAccount.setUuid(uuid);
			updatedAccount.getPerson().setPersonId(account.get().getPerson().getPersonId());
			
			return new AccountResponseDto ("UPDATED",new AccountDto(accountRepository.saveAndFlush(updatedAccount)));
		}
		return new AccountResponseDto ("FAILED",accountDto);
	}

	public String deleteAccount(String uuid) {
		String status = "Failed";
		Optional<Account> account = accountRepository.findById(uuid);
		if (account.isPresent()) {
			accountRepository.delete(account.get());
			status = "Success";
		}
		return status;
	}
}
