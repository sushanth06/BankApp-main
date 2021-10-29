package com.example.bankservices.service.impl;

import com.example.bankservices.dto.AccountDto;
import com.example.bankservices.dto.PaymeFundTransferDto;
import com.example.bankservices.entity.FundTransfer;
import com.example.bankservices.entity.PaymeFundTransfer;
import com.example.bankservices.repository.AccountRepository;
import com.example.bankservices.repository.FundTransferRepository;
import com.example.bankservices.repository.PaymeFundTransferRepository;
import com.example.bankservices.service.AccountService;
import com.example.bankservices.service.PaymeFundTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymeFundTransferServiceImpl implements PaymeFundTransferService {
    @Autowired
    PaymeFundTransferRepository paymeFundTransferRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FundTransferRepository fundTransferRepository;

    public void intiateFundTransfer(PaymeFundTransferDto fundTransferDto) {
        updatePaymeFundTransfer(fundTransferDto);
        updateBankAccountFundTransfer(fundTransferDto);
        updateBankAccountData(fundTransferDto);
    }

    private void updatePaymeFundTransfer(PaymeFundTransferDto fundTransferDto) {
        PaymeFundTransfer paymeFundTransfer = new PaymeFundTransfer(fundTransferDto.getFromPhoneNumber(),fundTransferDto.getToPhoneNumber(),fundTransferDto.getAmountToTransfer(),fundTransferDto.getDescription());
        paymeFundTransferRepository.save(paymeFundTransfer);
    }

    private void updateBankAccountFundTransfer(PaymeFundTransferDto fundTransferDto) {
        AccountDto fromAccountDto = accountService.getAccountDataFromPhoneNumber(fundTransferDto.getFromPhoneNumber());
        AccountDto toAccountDto = accountService.getAccountDataFromPhoneNumber(fundTransferDto.getToPhoneNumber());

        FundTransfer fundTransfer = new FundTransfer(fromAccountDto.getAccountID(),toAccountDto.getAccountID(),fundTransferDto.getAmountToTransfer(),fundTransferDto.getDescription());
        fundTransferRepository.save(fundTransfer);
    }

    private void updateBankAccountData(PaymeFundTransferDto fundTransferDto) {
        AccountDto fromAccountDto = accountService.getAccountDataFromPhoneNumber(fundTransferDto.getFromPhoneNumber());
        AccountDto toAccountDto = accountService.getAccountDataFromPhoneNumber(fundTransferDto.getToPhoneNumber());

        long finalAmountOfSender = fromAccountDto.getAccountBalance() - fundTransferDto.getAmountToTransfer();
        accountRepository.updateAccount(fromAccountDto.getAccountID(), finalAmountOfSender);

        long finalAmountOfRecipient = toAccountDto.getAccountBalance() + fundTransferDto.getAmountToTransfer();
        accountRepository.updateAccount(toAccountDto.getAccountID(), finalAmountOfRecipient);
    }
}
