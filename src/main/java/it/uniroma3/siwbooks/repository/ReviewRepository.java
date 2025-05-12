package it.uniroma3.siwbooks.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwbooks.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

}
