package com.example.bankservices.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankservices.entity.FundTransfer;
import com.example.bankservices.repository.FundTransferRepository;
import com.example.bankservices.service.AccountStatementService;

@Service
public class AccountStatementImpl implements AccountStatementService {
	
	@Autowired
	FundTransferRepository fundTransferRepository;


	@Override
	public List<FundTransfer> getAccountStatementFromID(Long accountID) {
		return fundTransferRepository.findByFromAccountID(accountID);
	}
}

