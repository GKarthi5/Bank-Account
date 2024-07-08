package com.bank.account.validation;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomOpeningDateValidator implements ConstraintValidator<CustomOpeningDateValidation, LocalDate> {
    @Override
    public void initialize(CustomOpeningDateValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate openingDate, ConstraintValidatorContext context) {
        
    	LocalDate curDate = LocalDate.now();
    	int accountOpening =0;
		if ((openingDate != null) && (curDate != null)) {
			accountOpening = Period.between(openingDate, curDate).getDays();
		} 
        return accountOpening <= 30;
    }
}