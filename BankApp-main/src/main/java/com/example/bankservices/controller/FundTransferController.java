package com.example.bankservices.controller;


import com.example.bankservices.dto.PaymeFundTransferDto;
import com.example.bankservices.service.PaymeFundTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankservices.entity.FundTransfer;
import com.example.bankservices.service.FundTransferService;

@RestController
@RequestMapping("/fundTransfer")
public class FundTransferController {
	

	@Autowired
	FundTransferService fundTransferService ;

	@Autowired
	PaymeFundTransferService paymeFundTransferService ;
	
	@PostMapping
	public FundTransfer transferFund(@RequestBody FundTransfer fundTransfer) {
		return fundTransferService.transferFund(fundTransfer);
	}

	@PostMapping("/payMe")
	public void intiatePaymeTranfer(@RequestBody PaymeFundTransferDto fundTransferDto) {
		paymeFundTransferService.intiateFundTransfer(fundTransferDto);
	}
 
}
