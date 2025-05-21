package it.uniroma3.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siwbooks.service.AuthorService;

@Controller
public class AuthorController {

	@Autowired
	private AuthorService authorService; 
	
	@GetMapping("/authors")
	public String showBooks(Model model) {
		model.addAttribute("authors", this.authorService.getAuthors());
		return "authors.html";
	}
	
}
