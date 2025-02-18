package com.yana.stepanova.repository;

import com.yana.stepanova.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT t.* FROM transactions t INNER JOIN accounts acc "
            + "ON t.account_from_id = acc.id OR t.account_to_id = acc.id "
            + "WHERE acc.iban = :accountId ORDER BY t.transaction_date DESC "
            + "LIMIT 10", nativeQuery = true)
    List<Transaction> findAllByAccountId(@Param("accountId") String accountId);
}
