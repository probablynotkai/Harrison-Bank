package com.probablynotkai.harrison.customer;

import com.probablynotkai.harrison.transaction.Transaction;
import com.probablynotkai.harrison.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    //TODO Ensure that transactions are included in model and view
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

                model.put("row" + i+1 + "_recipient", String.valueOf(currentTransaction.getRecipientId()));
                model.put("row" + i+1 + "_amount", String.valueOf(currentTransaction.getAmountTransferred()));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                model.put("row" + i+1 + "_date", currentTransaction.getDate().format(formatter));
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

    public void addNewCustomer(Customer customer){
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByAccountId(customer.getAccountId()); // Check if customer exists
        if (optionalCustomer.isPresent()){
            throw new IllegalStateException("That customer already exists.");
        }
        customerRepository.save(customer); // Customer possesses the @Entity annotation which allows it to be saved by the JPA
    }
}
