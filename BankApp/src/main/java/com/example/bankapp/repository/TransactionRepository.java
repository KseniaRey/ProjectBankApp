package com.example.bankapp.repository;

import com.example.bankapp.entity.Transaction;
import com.example.bankapp.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("select t from Transaction t where t.type = :type") // выполняет логику запроса
    List<Transaction> findByType(@Param("type") TransactionType type);
}
