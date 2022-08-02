package com.probablynotkai.harrison.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        return new ModelAndView("login");
    }

    @GetMapping(path = "/api")
    public Customer getCustomer(@RequestParam(value = "accountId") final String accountId){
        return customerService.getCustomer(accountId);
    }

    // TODO: Find a way to associate Customer entity with the login field
    // TODO: Once Customer entity is associated with LoginForm, display Accounts page
    // TODO: Ensure that the service endpoints aren't messed up

    @GetMapping(path = "/accounts")
    public String getCustomerPage(@RequestParam(value = "name") String name, @RequestParam(value = "surname") String surname){
        System.out.println("Username is " + name + " and surname is " + surname);
        return "account";
    }

    /*
    @GetMapping(path = "/accounts")
    public ModelAndView getCustomerPage(@RequestParam(value = "accountId", defaultValue = "-1") final String accountId){
        Customer customer = customerService.getCustomer(accountId);

        return customerService.getModelAndView(customer);
    }
     */

    @PutMapping(path = "/new_customer", produces = "application/json")
    public Customer createNewCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
        return customer;
    }
}
