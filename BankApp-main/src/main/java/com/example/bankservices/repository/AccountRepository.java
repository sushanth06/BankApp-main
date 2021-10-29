package com.example.bankservices.repository;

import com.example.bankservices.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Long> {
   public Account findByUserID(Long userID);

   public Account findByAccountID(Long accountID);

   @Transactional
   @Modifying(clearAutomatically = true)
   @Query("UPDATE Account c SET c.accountBalance = :balance WHERE c.accountID = :accountID")
   public void updateAccount(@Param("accountID") long accountID, @Param("balance") long balance);

}
