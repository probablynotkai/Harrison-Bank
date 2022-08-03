package com.probablynotkai.harrison.customer;

import com.probablynotkai.harrison.transaction.Transaction;
import com.probablynotkai.harrison.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CustomerService
{
    private final CustomerRepository customerRepository;
    private final TransactionService transactionService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, TransactionService transactionService){
        this.customerRepository = customerRepository;
        this.transactionService = transactionService;
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

        List<Transaction> transactions = transactionService.getTransactions(customer.getAccountId());
        if (!transactions.isEmpty()){
            Transaction[] recentTransactions = transactions.toArray(new Transaction[10]);

            for (int i = 0; i < recentTransactions.length-1; i++){
                Transaction currentTransaction = recentTransactions[i];

                if (currentTransaction == null) continue;

                Customer recipient = customerRepository.findCustomerByAccountId(currentTransaction.getRecipientId())
                        .orElseThrow(() -> new IllegalStateException("An unknown error has occurred."));
                model.put("row" + (i+1) + "_recipient", recipient.getName());

                Formatter doubleFormatter = new Formatter();
                doubleFormatter.format("%.2f", currentTransaction.getAmountTransferred());
                model.put("row" + (i+1) + "_amount", "£" + doubleFormatter);

                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                model.put("row" + (i+1) + "_date", currentTransaction.getDate().format(dateFormatter));
            }
        }

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

    public Customer getCustomerByEmail(String email){
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByEmail(email);
        if (optionalCustomer.isEmpty()){
            throw new IllegalStateException("This customer doesn't exist.");
        }
        return optionalCustomer.get();
    }

    public void addNewCustomer(Customer customer){
        Optional<Customer> idCustomer = customerRepository.findCustomerByAccountId(customer.getAccountId()); // Check if customer exists
        Optional<Customer> emailCustomer = customerRepository.findCustomerByEmail(customer.getEmail()); // Check if customer exists
        if (idCustomer.isPresent()){
            throw new IllegalStateException("That customer already exists.");
        }
        if (emailCustomer.isPresent()){
            throw new IllegalStateException("That customer already exists.");
        }

        customerRepository.save(customer); // Customer possesses the @Entity annotation which allows it to be saved by the JPA
    }
}
