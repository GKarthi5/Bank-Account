package com.bank.account.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AccountException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AccountException(String message) {
		super(message);
	}

}
