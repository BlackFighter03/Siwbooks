package it.uniroma3.siwbooks.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siwbooks.model.Author;
import it.uniroma3.siwbooks.model.ImageEntity;
import it.uniroma3.siwbooks.model.User;
import it.uniroma3.siwbooks.service.AuthorService;
import it.uniroma3.siwbooks.service.BookService;
import it.uniroma3.siwbooks.service.CredentialsService;
import it.uniroma3.siwbooks.service.ImageEntityService;
import it.uniroma3.siwbooks.service.UserService;
import jakarta.validation.Valid;

@Controller
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@Autowired
	private UserService userService;

	@GetMapping("/authors")
	public String showAuthors(Model model) {
		model.addAttribute("authors", this.authorService.getAuthors());
		return "authors.html";
	}

	@GetMapping("/authors/{id}")
	public String showAuthor(@PathVariable("id") Long id, Model model) {
		Author author = this.authorService.getAuthor(id);
		if (author == null)
			return "error.html";
		model.addAttribute("author", author);
		return "author.html";
	}

	@GetMapping("/user/authors")
	public String showAuthorsForUser(Model model) {
		User user = userService.getCurrentUser();
		if(user == null)
			return "redirect:/login";
		model.addAttribute("authors", this.authorService.getAuthors());
		model.addAttribute("user", user);
		return "user/authors.html";
	}

	@GetMapping("/user/authors/{id}")
	public String showAuthorForUser(@PathVariable("id") Long id, Model model) {
		Author author = this.authorService.getAuthor(id);
		if (author == null)
			return "error.html";
		User user = userService.getCurrentUser();
		if(user == null)
			return "redirect:/login";
		model.addAttribute("user", user);
		model.addAttribute("author", author);
		return "user/author.html";
	}
	
}
