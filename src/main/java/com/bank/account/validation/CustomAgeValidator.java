package com.bank.account.validation;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomAgeValidator implements ConstraintValidator<CustomAgeValidation, LocalDate> {
    @Override
    public void initialize(CustomAgeValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate dob, ConstraintValidatorContext context) {
        
    	LocalDate curDate = LocalDate.now();
    	int age =0;
		if ((dob != null) && (curDate != null)) {
			age = Period.between(dob, curDate).getYears();
		} 
        return age >= 18;
    }
}