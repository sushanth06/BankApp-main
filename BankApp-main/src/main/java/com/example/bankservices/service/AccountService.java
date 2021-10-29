package com.example.bankservices.service;

import com.example.bankservices.dto.AccountDto;
import com.example.bankservices.entity.Account;
public interface AccountService {

    public AccountDto getAccountDataFromPhoneNumber(String phoneNumber);

}