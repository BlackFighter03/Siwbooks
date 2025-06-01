package it.uniroma3.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import it.uniroma3.siwbooks.model.Book;
import it.uniroma3.siwbooks.model.Survey;
import it.uniroma3.siwbooks.model.User;
import it.uniroma3.siwbooks.service.*;


@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	private UserService userService;
	
	private boolean verifyBookAndUser(Book book, User user) {
		return book != null && user != null;
	}
	
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
	
	@GetMapping("/user/books")
	public String showBooksForUser(Model model) {
		User user = userService.getCurrentUser(); 
		if(user == null)
			return "redirect:/login";
		model.addAttribute("user", user);
		model.addAttribute("books", this.bookService.getBooks());
		return "user/books.html";
	}

	/**
     * Gestisce la visualizzazione dettagli libro
     */
    @GetMapping("/user/books/{id}")
    public String showBookForUser(@PathVariable("id") Long id, Model model) {
        Book book = this.bookService.getBook(id);
        User user = userService.getCurrentUser(); 
        if (!verifyBookAndUser(book, user)) {
            return "redirect:/login";
        }
        
        // Verifica se l'utente corrente ha già recensito questo libro
        Survey surveyUser = this.surveyService.findByUserAndBook(user, book);
        model.addAttribute("user", user);
        model.addAttribute("book", book);
        model.addAttribute("surveyUser", surveyUser);
        
        return "user/book.html";
    }
}
