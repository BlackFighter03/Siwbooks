package it.uniroma3.siwbooks.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwbooks.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
