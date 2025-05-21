package it.uniroma3.siwbooks.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwbooks.model.Survey;

public interface SurveyRepository extends CrudRepository<Survey, Long> {

}
