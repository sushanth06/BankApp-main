package com.example.bankservices.service.impl;

import com.example.bankservices.entity.Account;
import com.example.bankservices.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankservices.entity.FundTransfer;
import com.example.bankservices.entity.User;
import com.example.bankservices.repository.FundTransferRepository;
import com.example.bankservices.repository.UserRepository;
import com.example.bankservices.service.FundTransferService;

import java.util.Optional;

@Service
public class FundTransferImpl implements FundTransferService {
	@Autowired
	FundTransferRepository fundTransferRepository;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	
	public FundTransfer transferFund(FundTransfer fundTransfer) {
		validateAndMakeTransaction(fundTransfer);
		return fundTransferRepository.save(fundTransfer);
	}

	private void validateAndMakeTransaction(FundTransfer fundTransfer) throws IllegalArgumentException {
		// first check if from and to accountids exists
		// Check if balance is available
		// change balance in both the account -> add/remove
		long fromAccountID = fundTransfer.getFromAccountID();
		long toAccountID = fundTransfer.getToAccountID();
		long amountToTransfer = fundTransfer.getAmountToTransfer();

		Account fromAccount = accountRepository.findByAccountID(fromAccountID);
		Account toAccount = accountRepository.findByAccountID(toAccountID);


		Optional<User> fromUser = userRepository.findById(fromAccount.getUserID());
		Optional<User> toUser = userRepository.findById(toAccount.getUserID());

		if (!fromUser.isPresent() && !toUser.isPresent()) {
			throw new IllegalArgumentException("Both Sender account and Receipient account doesn't exists,Please double check");
		} else if (!fromUser.isPresent()) {
			throw new IllegalArgumentException("Sender account doesn't exists,Please double check fromAccountID");
		} else if (!toUser.isPresent()) {
			throw new IllegalArgumentException("Recepient account doesn't exists, Please double check toAccountID");
		} else if (fromAccount.getAccountBalance() < amountToTransfer) {
			throw new IllegalArgumentException("Funds are not sufficient to make this trnasaction,Please load funds");
		} else if (amountToTransfer == 0) {
			throw new IllegalArgumentException("Please add amount to transfer");
		}
		
		long finalAmountOfSender = fromAccount.getAccountBalance() - amountToTransfer;
		accountRepository.updateAccount(fromAccountID, finalAmountOfSender);

		long finalAmountOfRecipient = toAccount.getAccountBalance() + amountToTransfer;
		accountRepository.updateAccount(toAccountID, finalAmountOfRecipient);
	}
	
}
