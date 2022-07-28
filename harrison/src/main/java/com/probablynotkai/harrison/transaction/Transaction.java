package com.probablynotkai.harrison.transaction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction
{
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
    )
    private long transactionId;
    private long recipientId;
    private long senderId;
    private double amountTransferred;
    private LocalDateTime date;

    public Transaction(){
    }

    public Transaction(long transactionId, long recipientId, long senderId, double amountTransferred, LocalDateTime date){
        this.transactionId = transactionId;
        this.recipientId = recipientId;
        this.senderId = senderId;
        this.amountTransferred = amountTransferred;
        this.date = date;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(long recipientId) {
        this.recipientId = recipientId;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public double getAmountTransferred() {
        return amountTransferred;
    }

    public void setAmountTransferred(double amountTransferred) {
        this.amountTransferred = amountTransferred;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", recipientId=" + recipientId +
                ", senderId=" + senderId +
                ", amountTransferred=" + amountTransferred +
                ", date=" + date +
                '}';
    }
}