package com.example.bankservices.repository;

import com.example.bankservices.entity.PaymeFundTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymeFundTransferRepository extends JpaRepository<PaymeFundTransfer, Long> {
}
