package it.uniroma3.siwbooks.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwbooks.model.RegisteredUser;

public interface RegisteredUserRepository extends CrudRepository<RegisteredUser, Long> {

}
