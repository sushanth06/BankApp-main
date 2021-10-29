package com.example.bankservices.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankservices.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByPhone(String phone);
}
