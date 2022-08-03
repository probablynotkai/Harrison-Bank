package com.probablynotkai.harrison.registry.register;

import com.probablynotkai.harrison.account.AccountType;
import com.probablynotkai.harrison.customer.Customer;
import com.probablynotkai.harrison.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService
{
    private final CustomerService customerService;

    @Autowired
    public RegisterService(CustomerService customerService){
        this.customerService = customerService;
    }

    public Customer registerUser(RegisterForm registerForm){
        Customer customer = new Customer();
        customer.setName(registerForm.getName());
        customer.setEmail(registerForm.getEmail());
        customer.setPassword(registerForm.getPassword());
        customer.setAvailableBalance(0.0);
        customer.setInterestRate(1.0);
        customer.setAccountType(AccountType.LIMITED);
        customerService.addNewCustomer(customer);
        return customerService.getCustomerByEmail(registerForm.getEmail());
    }
}
