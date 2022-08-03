package com.probablynotkai.harrison.registry.register;

import com.probablynotkai.harrison.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterHandler
{
    private final RegisterService registerService;

    @Autowired
    public RegisterHandler(RegisterService registerService){
        this.registerService = registerService;
    }

    @GetMapping
    public ModelAndView getRegisterPage(){
        ModelAndView modelAndView = new ModelAndView("sign_up");
        modelAndView.getModelMap().addAttribute("registerform", new RegisterForm());
        return modelAndView;
    }

    @PostMapping("/validate")
    public ModelAndView validateRegistration(@ModelAttribute("registerform") RegisterForm registerForm, Model model){
        Customer newCustomer = registerService.registerUser(registerForm);
        return new ModelAndView("redirect:/customer/accounts/?accountId=" + newCustomer.getAccountId(), model.asMap());
    }
}
