package com.probablynotkai.harrison.customer;

import com.probablynotkai.harrison.account.AccountType;

import javax.persistence.*;

@Entity
public class Customer
{
    private String name;
    private String email;
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private long accountId;
    private AccountType accountType;
    private double availableBalance;
    private double interestRate;

    public Customer(long accountId, String name, String email, AccountType accountType, int availableBalance, double interestRate){
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.accountType = accountType;
        this.availableBalance = availableBalance;
        this.interestRate = interestRate;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", accountId=" + accountId +
                ", accountType=" + accountType +
                ", availableBalance=" + availableBalance +
                ", interestRate=" + interestRate +
                '}';
    }
}
