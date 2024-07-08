package com.bank.account.validation;

import java.time.Period;

import com.bank.account.dto.AccountDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountClosureValidator implements ConstraintValidator<AccountClosureAnnotation, AccountDto> {
	public void initialize(AccountClosureAnnotation constraintAnnotation) {
	}

	public boolean isValid(AccountDto object, ConstraintValidatorContext context) {
		if (!(object instanceof AccountDto)) {
			throw new IllegalArgumentException("@AddressAnnotation only applies to Address objects");
		}
		AccountDto account = (AccountDto) object;
		boolean isValid = false;
		if (account.isTemporaryAccount()) {
			int accountOpening = 0;
			if ((account.getClosureDate() != null) && account.getOpeningDate() != null) {
				accountOpening = Period.between(account.getClosureDate(), account.getOpeningDate()).getDays();
			}
			return accountOpening <= 60;
		} else {
			// check address.getZipCode() structure for Greece
			// no zipcode / city correlation available at the moment
			return isValid;
		}
		// ...
	}
}