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
    private LoginService loginService;

    private static final String LOGIN_VIEW = "login";

    @Autowired
    public LoginHandler(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLoginForm(@ModelAttribute("loginform") LoginForm loginForm){
        return LOGIN_VIEW;
    }

    @PostMapping("/login")
    public String submitLoginForm(@ModelAttribute("loginform") LoginForm loginForm, RedirectAttributes ra){
        ra.addFlashAttribute("currentUser", loginForm);
        return "redirect:/validate";
    }

    @GetMapping("/validate")
    public String getValidatePage(){
        return "account";
    }

    @PostMapping("/validate")
    public String validateLoginForm(@ModelAttribute("loginform") LoginForm loginForm){
        // TODO: finish form validation
        return "null";
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("loginform", new LoginForm());
    }
}
