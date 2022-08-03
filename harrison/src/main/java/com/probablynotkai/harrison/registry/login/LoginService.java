package com.probablynotkai.harrison.registry.login;

import com.probablynotkai.harrison.customer.Customer;
import com.probablynotkai.harrison.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class LoginService
{
    private final CustomerService customerService;

    @Autowired
    public LoginService(CustomerService customerService){
        this.customerService = customerService;
    }

    // TODO: Adjust validation to throw errors on invalid sign in
    @Nullable
    public Customer validateLoginForm(LoginForm loginForm){
        try{
            Customer customerForEmail = customerService.getCustomerByEmail(loginForm.getEmail());

            if (customerForEmail.getPassword().equals(loginForm.getPassword())){
                return customerForEmail;
            }

            return null;
        } catch (IllegalStateException e){
            return null;
        }
    }
}
