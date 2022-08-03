package com.probablynotkai.harrison.registry.login;

import com.probablynotkai.harrison.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginHandler
{
    private final LoginService loginService;

    private static final String LOGIN_VIEW = "login";

    @Autowired
    public LoginHandler(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/customer/login")
    public ModelAndView getLoginForm(){
        ModelAndView modelAndView = new ModelAndView(LOGIN_VIEW);
        modelAndView.getModelMap().addAttribute("loginform", new LoginForm());
        return modelAndView;
    }

    @PostMapping("/customer/validate")
    public ModelAndView submitLoginForm(@ModelAttribute("loginform") LoginForm loginForm, Model model){
        Customer validatedCustomer = loginService.validateLoginForm(loginForm);

        if (validatedCustomer != null){
            return new ModelAndView("redirect:/customer/accounts?accountId=" + validatedCustomer.getAccountId(), model.asMap());
        }
        return new ModelAndView("redirect:/customer/login");
    }

    @GetMapping("/customer/validate")
    public String returnValidatePage(){
        return "redirect:/customer/login";
    }
}
