package com.probablynotkai.harrison.registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginHandler
{
    private final LoginService loginService;

    private static final String LOGIN_VIEW = "login";

    @Autowired
    public LoginHandler(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLoginForm(Model model){
        model.addAttribute("loginform", new LoginForm());
        return LOGIN_VIEW;
    }

    @PostMapping("/validate")
    public String submitLoginForm(@ModelAttribute("loginform") LoginForm loginForm, Model model){
        if (loginService.validateLoginForm(loginForm)){
            return "redirect:/customer/accounts?name=" + loginForm.getUsername() + "&surname=" + loginForm.getPassword();
        }
        return "redirect:/login";
    }

    @GetMapping("/validate")
    public String returnValidatePage(){
        return "redirect:/login";
    }
}
