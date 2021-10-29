package com.example.bankservices.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@Entity
public class PaymeFundTransfer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromPhoneNumber;

    private String toPhoneNumber;

    private int amountToTransfer;

    private LocalDate transactionDate  = LocalDate.now();

    private String description;

    public PaymeFundTransfer(String fromPhoneNumber, String toPhoneNumber, int amountToTransfer, String description) {
        this.fromPhoneNumber = fromPhoneNumber;
        this.toPhoneNumber = toPhoneNumber;
        this.amountToTransfer = amountToTransfer;
        this.description = description;
    }

    public PaymeFundTransfer() {
        super();
    }
}

