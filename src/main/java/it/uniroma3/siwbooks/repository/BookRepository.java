package it.uniroma3.siwbooks.repository;


import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwbooks.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
