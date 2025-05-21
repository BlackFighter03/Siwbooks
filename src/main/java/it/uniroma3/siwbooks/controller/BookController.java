package it.uniroma3.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siwbooks.service.BookService;


@Controller
public class BookController {

	@Autowired
	private BookService bookService; 
	
	@GetMapping("/books")
	public String showBooks(Model model) {
		model.addAttribute("books", this.bookService.getBooks());
		return "books.html";
	}
	
	@GetMapping("/books/{id}")
	public String showBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", this.bookService.getBook(id));
		return "book.html";
	}
	
	
}
