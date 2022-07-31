package com.probablynotkai.harrison;

import com.probablynotkai.harrison.registry.LoginForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Controller
public class HarrisonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarrisonApplication.class, args);
	}

	@GetMapping
	public ModelAndView home(){
		return new ModelAndView("login");
	}


	/*
	Using ModelAndView:
	@GetMapping("/")
	public ModelAndView home(){
		Map<String, String> model = new HashMap<>();
		model.put("header_placeholder", "Test");
		return new ModelAndView("account", model);
	}
	 */
}
