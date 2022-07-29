package com.probablynotkai.harrison.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService
{
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions(Long senderId){
        return transactionRepository.findTransactionsBySenderId(senderId).orElse(new ArrayList<>());
    }

    public Transaction getTransaction(Long transactionId){
        return transactionRepository.findTransactionByTransactionId(transactionId)
                .orElseThrow(() -> new IllegalStateException("This transaction doesn't exist"));
    }

    public Transaction getTransaction(String transactionId){
        try {
            return getTransaction(Long.parseLong(transactionId));
        } catch (NumberFormatException e){
            throw new IllegalStateException("This transaction doesn't exist.");
        }
    }

    public void addTransaction(Transaction transaction){
        Optional<Transaction> optionalTransaction = transactionRepository.findTransactionByTransactionId(transaction.getTransactionId());
        if (optionalTransaction.isPresent()){
            throw new IllegalStateException("This transaction already exists.");
        }
        transactionRepository.save(transaction);
    }

    public void deleteTransaction(Transaction transaction){
        Optional<Transaction> optionalTransaction = transactionRepository.findTransactionByTransactionId(transaction.getTransactionId());
        if (optionalTransaction.isEmpty()){
            throw new IllegalStateException("This transaction doesn't exist.");
        }
        transactionRepository.delete(transaction);
    }

    public void deleteTransaction(String transactionId){
        try {
            deleteTransaction(transactionRepository.findTransactionByTransactionId(Long.parseLong(transactionId))
                    .orElseThrow(() -> new IllegalStateException("This transaction already doesn't exist.")));
        } catch (NumberFormatException e){
            throw new IllegalStateException("This transaction doesn't exist.");
        }
    }

    @Transactional
    public void updateTransaction(Long transactionId, double amount){
        Transaction optionalTransaction = transactionRepository.findTransactionByTransactionId(transactionId)
                .orElseThrow(() -> new IllegalStateException("This transaction doesn't exist."));

        if (optionalTransaction.getAmountTransferred() != amount){
            optionalTransaction.setAmountTransferred(amount);
        }
    }
}
