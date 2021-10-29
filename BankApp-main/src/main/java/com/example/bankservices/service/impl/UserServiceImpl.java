package com.example.bankservices.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.example.bankservices.dto.AccountDto;
import com.example.bankservices.dto.UserDto;
import com.example.bankservices.entity.Account;
import com.example.bankservices.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankservices.entity.User;
import com.example.bankservices.repository.UserRepository;
import com.example.bankservices.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;

	@Override
	public User addUser(UserDto userDto, AccountDto accountDto){
		User user = convertUserDtoToEntity(userDto);
		validateUserData(user);
		User savedUserData = userRepository.save(user);

		Account account = convertAccountDtoToEntity(accountDto);
		account.setUserID(savedUserData.getId());
		accountRepository.save(account);
		return user;
	}

	
	@Override
	public List<UserDto> getAllUsers(){
		return userRepository.findAll()
				.stream()
				.map(this::convertUserEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public UserDto convertUserEntityToDto(User user) {
		UserDto userDto = new UserDto(user.getFirstName(),user.getLastName(),user.getAge(), user.getPan(),user.getPhone(),user.getEmail(),user.getGender());
		return  userDto;
	}

	public User convertUserDtoToEntity(UserDto userDto) {
		User user = new User(userDto.getFirstName(),userDto.getLastName(),userDto.getAge(), userDto.getPan(),userDto.getPhone(),userDto.getEmail(),userDto.getGender());
		return  user;
	}

	public  Account convertAccountDtoToEntity(AccountDto accountDto) {
		Account account = new Account(accountDto.getAccountBalance());
		return account;
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
	
	private void validateUserData(User user) throws IllegalArgumentException {
        if (user.getFirstName() == null || user.getFirstName().length() == 0) {
            throw new IllegalArgumentException("First Name is mandatory");
        } else  if (user.getLastName() == null || user.getLastName().length() == 0) {
            throw new IllegalArgumentException("Last Name is mandatory");
        }else if (user.getAge() < 18) {
            throw new IllegalArgumentException("Minimum age requirement is 18 to create an account");
        } 
    }
}
 

