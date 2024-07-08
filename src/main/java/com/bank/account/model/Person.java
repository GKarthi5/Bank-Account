package com.bank.account.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personId;
	
	@Column
	@NotBlank(message = "First name is required")
	@Size(min = 3, max = 35, message = "First name should be between 3 and 35 characters")
	private String firstName;
	
	@Column(length = 35)
	@NotBlank(message = "Last name is required")
	@Size(min = 3, max = 35, message = "Last name should be between 3 and 35 characters")
	private String lastName;
	
	private LocalDate dob;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email is invalid")
	private String emailId;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid", referencedColumnName = "uuid",  insertable = true, updatable = true)
    private Account account;

}
