package it.uniroma3.siwbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwbooks.model.Book;
import it.uniroma3.siwbooks.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getBooks() {
		return (List<Book>) this.bookRepository.findAll();
	}

	public Book getBook(Long id) {
		return bookRepository.findById(id).get();
	}

	public void saveBook(Book book) {
		this.bookRepository.save(book);
	}

}
