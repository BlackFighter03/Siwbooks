package it.uniroma3.siwbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwbooks.model.ImageEntity;
import it.uniroma3.siwbooks.repository.ImageEntityrRepository;

@Service
public class ImageEntityService {

	@Autowired
    private ImageEntityrRepository imageEntityRepository;
	
    public ImageEntity getImage(Long id) {
        return imageEntityRepository.findById(id).orElse(null);
    }
}
