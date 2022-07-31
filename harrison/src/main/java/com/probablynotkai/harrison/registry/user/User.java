package com.probablynotkai.harrison.registry.user;

import com.probablynotkai.harrison.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

public class User
{
    // TODO: Figure out OneToOne mapping between User.java and Customer.java
    @OneToOne(mappedBy = "accountId")
    @JoinColumn(name = "account_id")
    private Customer customer;
    private String username;
    private String password;

    @Autowired
    public User(String username, String password, Customer customer){
        this.username = username;
        this.password = password;
        this.customer = customer;
    }

    public User(){}
}
