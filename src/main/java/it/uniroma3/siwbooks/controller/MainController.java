package it.uniroma3.siwbooks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

	@GetMapping("/")
	public String getHome() {
		return "index.html";
	}
	
	
	
}
