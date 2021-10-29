package com.example.bankservices.dto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
	@NotNull
	@NotEmpty
 	private String firstName;
 	private String lastName;
 	private int age;
 	private String pan;
 	private String phone;
 	private String email;
 	private String gender;

	public UserDto(String firstName, String lastName, int age, String pan, String phone, String email, String gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.pan = pan;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
	}
}
