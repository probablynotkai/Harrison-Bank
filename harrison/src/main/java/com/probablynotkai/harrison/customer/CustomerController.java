package com.probablynotkai.harrison.customer;

import com.probablynotkai.harrison.transaction.TransactionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public ModelAndView getLoginPage(){
        return new ModelAndView("index");
    }

    @GetMapping(path = "/api")
    public Customer getCustomer(@RequestParam(value = "accountId") final String accountId){
        return customerService.getCustomer(accountId);
    }

    @GetMapping(path = "/accounts")
    public ModelAndView getCustomerPage(@RequestParam(value = "accountId", defaultValue = "-1") final String accountId){
        Customer customer = customerService.getCustomer(accountId);

        return customerService.getModelAndView(customer);
    }

    @PutMapping(path = "/new_customer", produces = "application/json")
    public Customer createNewCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
        return customer;
    }
}
