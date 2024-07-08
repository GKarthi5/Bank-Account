package com.bank.account.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
	private String uuid;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	 
	private LocalDate openingDate;
	private LocalDate closureDate;
	private boolean isTemporaryAccount;
	private Long initialDeposit;
	
	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
	Person person;

}
