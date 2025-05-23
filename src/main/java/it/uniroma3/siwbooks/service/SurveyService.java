package it.uniroma3.siwbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwbooks.repository.SurveyRepository;

@Service
public class SurveyService {

	@Autowired
	private SurveyRepository surveyRepository;
}
