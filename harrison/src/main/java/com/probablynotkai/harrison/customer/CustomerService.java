package com.probablynotkai.harrison.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService
{
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Nullable
    public Customer getCustomer(long accountId){
        Optional<Customer> customer = customerRepository.findCustomerByAccountId(accountId);
        if (customer.isEmpty()){
            return null;
        }
        return customer.get();
    }

    public ModelAndView getModelAndView(Customer customer) {
        if (customer == null){
            return getNoCustomerModelAndView();
        }

        Map<String, String> model = new HashMap<>();
        model.put("header_placeholder", customer.getName());
        model.put("available_balance", String.valueOf(customer.getAvailableBalance()));
        model.put("account_type", customer.getAccountType().normalize());
        model.put("interest_rate", String.valueOf(customer.getInterestRate()));

        return new ModelAndView("account", model);
    }

    public ModelAndView getNoCustomerModelAndView(){
        Map<String, String> model = new HashMap<>();
        model.put("time", LocalDateTime.now().toString());

        return new ModelAndView("no_customer", model);
    }

    public Customer getCustomer(String accountId){
        try {
            return getCustomer(Long.parseLong(accountId));
        } catch (NumberFormatException e){
            throw new IllegalStateException("Account ID entered is incorrect.");
        }
    }

    public void addNewCustomer(Customer customer){
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByAccountId(customer.getAccountId()); // Check if customer exists
        if (optionalCustomer.isPresent()){
            throw new IllegalStateException("That customer already exists.");
        }
        customerRepository.save(customer); // Customer possesses the @Entity annotation which allows it to be saved by the JPA
    }
}
