package com.probablynotkai.harrison.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
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

    @GetMapping(path = "/accounts")
    public ModelAndView getCustomerPage(@RequestParam(value = "accountId", defaultValue = "-1") final String accountId){
        Customer customer = customerService.getCustomer(accountId);

        return customerService.getModelAndView(customer);
    }

    @PutMapping(value = "/new_customer", headers = "text/plain")
    public void createNewCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
    }
}
