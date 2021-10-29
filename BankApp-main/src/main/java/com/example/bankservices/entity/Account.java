package com.example.bankservices.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountID = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;

    private long accountBalance = 10000; // setting default value to 10000(some min balance while creating account)

    private String accountType = "Savings";

    private  Long userID;// store user table unique id

    public Account(long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Account(){
        super();
    }
}
