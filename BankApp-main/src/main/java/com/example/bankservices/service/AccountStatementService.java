package com.example.bankservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.bankservices.entity.FundTransfer;

public interface AccountStatementService {

	public List<FundTransfer> getAccountStatementFromID(Long accountID);

}
