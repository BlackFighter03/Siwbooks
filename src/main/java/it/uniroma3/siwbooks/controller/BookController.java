package it.uniroma3.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siwbooks.model.Book;
import it.uniroma3.siwbooks.model.Survey;
import it.uniroma3.siwbooks.model.User;
import it.uniroma3.siwbooks.service.BookService;
import it.uniroma3.siwbooks.service.CredentialsService;
import it.uniroma3.siwbooks.service.SurveyService;
import it.uniroma3.siwbooks.service.UserService;
import jakarta.validation.Valid;


@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CredentialsService credentialsService;;
	
	private boolean verifyBookAndUser(Book book, User user) {
		return book != null && user != null;
	}
	
	private boolean verifyId(Long idUrl, Long idUser) {
		return idUser!= null && idUrl == idUser;
	}
	
	private boolean verifyAdmin(User admin) {
		return credentialsService.isAdminCurrent(admin);
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
    /**
     * Gestisce l'inserimento di una nuova recensione
     */
    @PostMapping("/user/{userId}/books/{bookId}/survey")
    public String addSurvey(@PathVariable("userId") Long userId,
    					  @PathVariable("bookId") Long bookId,
                          @RequestParam("title") String title,
                          @RequestParam("text") String text,
                          @RequestParam("mark") Integer mark,
                          Model model) {
        
        Book book = this.bookService.getBook(bookId);
        User user = this.userService.getCurrentUser();
        if (!verifyBookAndUser(book, user) || !verifyId(user.getId(), userId)) {
            return "redirect:/login";
        }
        try {
            this.surveyService.saveSurvey(new Survey(title, mark, text, book, user));
            return "redirect:/user/books/" + bookId;
            
        } catch (Exception e) {
        	model.addAttribute("book", book);
        	model.addAttribute("user", user);
            model.addAttribute("errorMessage", "Errore nell'inserimento della recensione: " + e.toString());
            return "user/book.html";
        }
    }
    
    @PostMapping("/user/books/{bookId}/survey/{surveyId}/delete")
    public String deleteSurvey(@PathVariable("bookId") Long bookId,
    						@PathVariable("surveyId") Long surveyId,
                          Model model) {
        
        Book book = this.bookService.getBook(bookId);
        User user = this.userService.getCurrentUser();
        Survey survey = this.surveyService.getSurvey(surveyId);
        if (book == null || survey == null || !survey.getUser().equals(user)) {
            return "redirect:/login";
        }
        try {
        	book.getSurveys().remove(survey);
            this.surveyService.deleteSurvey(survey);
            this.bookService.saveBook(book);
            return "redirect:/user/books/" + bookId;
            
        } catch (Exception e) {
        	model.addAttribute("book", book);
        	model.addAttribute("user", user);
            model.addAttribute("errorMessage", "Errore nella cancellazione della recensione: " + e.toString());
            return "user/book.html";
        }
    }
    
    @GetMapping("/admin/books")
	public String showBooksForAdmin(Model model) {
		User user = userService.getCurrentUser(); 
		if(!this.verifyAdmin(user))
			return "redirect:/login";
		model.addAttribute("user", user);
		model.addAttribute("books", this.bookService.getBooks());
		return "admin/books.html";
	}

	/**
     * Gestisce la visualizzazione dettagli libro
     */
    @GetMapping("/admin/books/{id}")
    public String showBookForAdmin(@PathVariable("id") Long id, Model model) {
        Book book = this.bookService.getBook(id);
        User user = userService.getCurrentUser(); 
        if (!verifyBookAndUser(book, user) || !this.verifyAdmin(user)) {
            return "redirect:/login";
        }
        
        // Verifica se l'utente corrente ha già recensito questo libro
        Survey surveyUser = this.surveyService.findByUserAndBook(user, book);
        model.addAttribute("user", user);
        model.addAttribute("book", book);
        model.addAttribute("surveyUser", surveyUser);
        
        return "admin/book.html";
    }
    
    @PostMapping("/admin/books/{bookId}/survey/{surveyId}/delete")
    public String deleteSurveyForAdmin(@PathVariable("bookId") Long bookId,
    						@PathVariable("surveyId") Long surveyId,
                          Model model) {
        
        Book book = this.bookService.getBook(bookId);
        User user = this.userService.getCurrentUser();
        Survey survey = this.surveyService.getSurvey(surveyId);
        if (book == null || survey == null || !verifyAdmin(user)) {
            return "redirect:/login";
        }
        try {
        	book.getSurveys().remove(survey);
            this.surveyService.deleteSurvey(survey);
            this.bookService.saveBook(book);
            return "redirect:/admin/books/" + bookId;
            
        } catch (Exception e) {
        	model.addAttribute("book", book);
        	model.addAttribute("user", user);
            model.addAttribute("errorMessage", "Errore nella cancellazione della recensione: " + e.toString());
            return "admin/book.html";
        }
    }
	
}
