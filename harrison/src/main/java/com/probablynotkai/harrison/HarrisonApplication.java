package com.probablynotkai.harrison;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Controller
public class HarrisonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarrisonApplication.class, args);
	}

	@GetMapping("/")
	public ModelAndView home(){
		return new ModelAndView("index");
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
