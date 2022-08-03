package com.probablynotkai.harrison.customer;

import com.probablynotkai.harrison.registry.login.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

    // TODO: Ensure that the service endpoints aren't messed up
    // TODO: Login Validation
    // TODO: Logout button/menu
    // TODO: Sending money
    // TODO: Research cookies
    @GetMapping(path = "/accounts")
    public ModelAndView getCustomerPage(@RequestParam(value = "accountId", defaultValue = "-1") final String accountId, Model model){
        Customer customer = customerService.getCustomer(accountId);

        return customerService.getModelAndView(customer);
    }

    @PutMapping(path = "/new_customer", produces = "application/json")
    public Customer createNewCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
        return customer;
    }
}
