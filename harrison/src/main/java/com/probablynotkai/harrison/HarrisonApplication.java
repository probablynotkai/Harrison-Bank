package com.probablynotkai.harrison;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class HarrisonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarrisonApplication.class, args);
	}

	@GetMapping
	public String home(){
		return "redirect:/customer/login";
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
