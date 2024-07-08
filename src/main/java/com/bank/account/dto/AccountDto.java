package com.bank.account.dto;

import java.time.LocalDate;
import java.util.List;

import com.bank.account.model.Account;
import com.bank.account.model.AccountType;
import com.bank.account.model.Person;
import com.bank.account.validation.AccountClosureAnnotation;
import com.bank.account.validation.CustomAgeValidation;
import com.bank.account.validation.CustomOpeningDateValidation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@AccountClosureAnnotation
public class AccountDto {

	private String uuid;

	@CustomOpeningDateValidation(message = "Account opening date should not be more or less than past 30 days ")
	private LocalDate openingDate;

	private LocalDate closureDate;

	private boolean isTemporaryAccount;
	
	@Min(value = 0, message = "Initial amount must not be in negative value")
	private Long initialDeposit;

	@NotEmpty(message = "Please enter first name")
	@Size(min = 3, max = 35, message = "Please enter the first name between 3 to 35 characters")
	private String firstName;

	@NotEmpty(message = "Please enter last name")
	@Size(min = 3, max = 35, message = "Please enter the last name between 3 to 35 characters")
	private String lastName;

	@NotEmpty(message = "Please enter the email addresss")
	@Email(message = "Please provide a valid email address")
	private String email;

	@Past(message = "date of birth must be in the past")
	@CustomAgeValidation(message = "Age must be greater than 18")
	private LocalDate dob;

	private String accountType;
	
	private List<FormFieldError> formErrors;
	
	public Account toEntity() {

		Account account = new Account();
		account.setClosureDate(closureDate);
		account.setOpeningDate(openingDate);
		account.setInitialDeposit(initialDeposit);
		account.setTemporaryAccount(isTemporaryAccount);
		account.setAccountType((null!=accountType&& "savings".equalsIgnoreCase(accountType)) ?
				AccountType.SAVING: AccountType.CURRENT);
		account.setUuid(uuid);
		
		Person person = new Person();

		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setEmailId(email);
		person.setDob(dob);
		person.setAccount(account);

		account.setPerson(person);

		return account;

	}

	public AccountDto(Account account) {
		this.uuid = account.getUuid();
		this.closureDate = account.getClosureDate();
		this.initialDeposit = account.getInitialDeposit();
		this.openingDate = account.getOpeningDate();
		this.accountType = account.getAccountType().name();
		this.isTemporaryAccount  = account.isTemporaryAccount();

		this.firstName = account.getPerson().getFirstName();
		this.lastName = account.getPerson().getLastName();
		this.email = account.getPerson().getEmailId();
		this.dob = account.getPerson().getDob();
	}

}
