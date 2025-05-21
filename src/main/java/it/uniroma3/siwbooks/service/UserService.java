package it.uniroma3.siwbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwbooks.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
}
