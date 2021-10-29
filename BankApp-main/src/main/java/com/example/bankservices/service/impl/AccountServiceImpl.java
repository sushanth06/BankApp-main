package com.example.bankservices.service.impl;

import com.example.bankservices.dto.AccountDto;
import com.example.bankservices.entity.Account;
import com.example.bankservices.entity.FundTransfer;
import com.example.bankservices.entity.User;
import com.example.bankservices.repository.AccountRepository;
import com.example.bankservices.repository.FundTransferRepository;
import com.example.bankservices.repository.UserRepository;
import com.example.bankservices.service.AccountService;
import com.example.bankservices.service.AccountStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public AccountDto getAccountDataFromPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhone(phoneNumber);
        if(user == null){
            return new AccountDto(0);
        }
        Account account = accountRepository.findByUserID(user.getId());
        if(account == null){
            return new AccountDto(0);
        }
        AccountDto accountDto = new AccountDto(account.getAccountBalance(),account.getAccountType(),account.getAccountID());
        return accountDto;
    }

}

