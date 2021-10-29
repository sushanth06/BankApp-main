package com.example.bankservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankservices.entity.FundTransfer;
import com.example.bankservices.service.AccountStatementService;
 
@RestController
@RequestMapping("/accountStatement")
public class AccountStatmentController {
	
	
	@Autowired
	AccountStatementService accountStatementService;
	
	@GetMapping("/{accountID}")
	public List<FundTransfer> getAccountStatementFromID(@PathVariable Long accountID) {
		return accountStatementService.getAccountStatementFromID(accountID);
	}
 
}