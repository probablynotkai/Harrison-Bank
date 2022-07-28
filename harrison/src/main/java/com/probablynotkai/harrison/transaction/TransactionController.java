package com.probablynotkai.harrison.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController
{
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/update")
    public void updateTransaction(@RequestParam(value = "transactionId", defaultValue = "-1") final String transactionId,
                                          @RequestParam(value = "amount") final String amount){
        try {
            Long.parseLong(transactionId);
            Double.parseDouble(amount);
        } catch (NumberFormatException e){
            throw new IllegalStateException("Unable to parse transaction ID or transaction amount.");
        }

        transactionService.updateTransaction(Long.parseLong(transactionId), Double.parseDouble(amount));
    }

    @PutMapping("/create")
    public void addTransaction(@RequestBody Transaction transaction){
        transactionService.addTransaction(transaction);
    }

    @DeleteMapping("/delete")
    public void deleteTransaction(@RequestParam(value = "transactionId", defaultValue = "-1") final String transactionId){
        transactionService.deleteTransaction(transactionId);
    }
}
