package com.example.bankservices.controller;

import java.util.List;

import com.example.bankservices.dto.AccountDto;
import com.example.bankservices.dto.UserDto;
import com.example.bankservices.entity.Account;
import com.example.bankservices.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import com.example.bankservices.entity.User;
import com.example.bankservices.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;


	@Autowired
	Environment environment;
	
	@PostMapping("/createUser")
	public User addUserParams(@RequestParam String firstName,
							  @RequestParam String lastName,
							  @RequestParam String phone,
							  @RequestParam int age,
							  @RequestParam String pan,
							  @RequestParam String email,
							  @RequestParam String gender,
							  @RequestParam(required = false,defaultValue = "10000") int initialAmount) {

		UserDto userDto = new UserDto(firstName,lastName,age,pan,phone,email,gender);
		AccountDto accountDto = new AccountDto(initialAmount);
		return userService.addUser(userDto,accountDto);
	}

	@GetMapping("/port")
	public String getPortNo() {
		String port = environment.getProperty("local.server.port");
		return "From PayMe app : " + port;
	}
	
	@GetMapping
	public List<UserDto> getAllUsers(){
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@GetMapping("/account/{phoneNumber}")
	public AccountDto getAccountByPhoneNumber(@PathVariable String phoneNumber) {
		return accountService.getAccountDataFromPhoneNumber(phoneNumber);
	}

	@DeleteMapping("/{id}")
	public String deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
		return "Success";
}
 
}