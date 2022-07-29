package com.probablynotkai.harrison.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>
{
    Optional<Transaction> findTransactionByRecipientId(Long recipientId);
    Optional<Transaction> findTransactionBySenderId(Long senderId);
    Optional<Transaction> findTransactionByTransactionId(Long transactionId);
    Optional<List<Transaction>> findTransactionsBySenderId(Long transactionId);
}
