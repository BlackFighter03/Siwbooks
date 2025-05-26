package it.uniroma3.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siwbooks.model.Author;
import it.uniroma3.siwbooks.model.User;
import it.uniroma3.siwbooks.service.AuthorService;
import it.uniroma3.siwbooks.service.CredentialsService;
import it.uniroma3.siwbooks.service.UserService;

@Controller
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	private boolean verifyAdmin(User admin) {
		return credentialsService.isAdminCurrent(admin);
	}
	
	@GetMapping("/authors")
	public String showAuthors(Model model) {
		model.addAttribute("authors", this.authorService.getAuthors());
		return "authors.html";
	}
	
	@GetMapping("/authors/{id}")
	public String showAuthor(@PathVariable("id") Long id, Model model) {
		Author author = this.authorService.getAuthor(id);
		if(author == null)
			return "redirect:/authors";
		model.addAttribute("author", author);
		return "author.html";
	}
	
	@GetMapping("/user/authors")
	public String showAuthorsForUser(Model model) {
		User user = userService.getCurrentUser();
		model.addAttribute("authors", this.authorService.getAuthors());
		model.addAttribute("user", user);
		return "user/authors.html";
	}
	
	@GetMapping("/user/authors/{id}")
	public String showAuthorForUser(@PathVariable("id") Long id, Model model) {
		Author author = this.authorService.getAuthor(id);
		if(author == null)
			return "redirect:/user/authors";
		User user = userService.getCurrentUser();
		model.addAttribute("user", user);
		model.addAttribute("author", author);
		return "user/author.html";
	}
	
	@GetMapping("/admin/authors")
	public String showAuthorsForAdmin(Model model) {
		User user = userService.getCurrentUser();
		if(!verifyAdmin(user))
			return "redirect:/login";
		model.addAttribute("authors", this.authorService.getAuthors());
		model.addAttribute("user", user);
		return "admin/authors.html";
	}
	
	@GetMapping("/admin/authors/{id}")
	public String showAuthorForAdmin(@PathVariable("id") Long id, Model model) {
		Author author = this.authorService.getAuthor(id);
		if(author == null)
			return "redirect:/user/authors";
		User user = userService.getCurrentUser();
		if(!verifyAdmin(user))
			return "redirect:/login";
		model.addAttribute("user", user);
		model.addAttribute("author", author);
		return "admin/author.html";
	}
}
