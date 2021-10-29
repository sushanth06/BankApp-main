package com.example.bankservices.service;

import java.util.List;

import com.example.bankservices.dto.AccountDto;
import com.example.bankservices.dto.UserDto;
import com.example.bankservices.entity.Account;
import com.example.bankservices.entity.User;

public interface UserService {

	public User addUser(UserDto userDto, AccountDto accountDto);

	public List<UserDto> getAllUsers();

	public User getUserById(Long id);

	public void deleteUserById(Long id);

	public UserDto convertUserEntityToDto(User user);
}
