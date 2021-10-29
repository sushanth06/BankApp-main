package com.example.bankservices.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PaymeFundTransferDto {
    private String fromPhoneNumber;
    private String toPhoneNumber;
    private int amountToTransfer;
    private String description;

    public PaymeFundTransferDto(String fromPhoneNumber, String toPhoneNumber, int amountToTransfer, LocalDateTime transactionDate, String description) {
        this.fromPhoneNumber = fromPhoneNumber;
        this.toPhoneNumber = toPhoneNumber;
        this.amountToTransfer = amountToTransfer;
        this.description = description;
    }
}