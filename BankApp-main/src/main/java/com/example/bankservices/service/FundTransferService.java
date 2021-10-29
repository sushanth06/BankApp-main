package com.example.bankservices.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.bankservices.entity.FundTransfer;

public interface FundTransferService {
	
	@Autowired
	public FundTransfer transferFund(FundTransfer fundTransfer);
}
