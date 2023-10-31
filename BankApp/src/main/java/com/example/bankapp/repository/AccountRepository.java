package com.example.bankapp.repository;

import com.example.bankapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query("SELECT acc FROM Account acc WHERE acc.agreement.product.name = :productName")
    List<Account> getByProductName(@Param("productName") String productName);
}
