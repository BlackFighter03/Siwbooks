package it.uniroma3.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siwbooks.model.Credentials;
import it.uniroma3.siwbooks.model.User;
import it.uniroma3.siwbooks.service.CredentialsService;
import jakarta.validation.Valid;


@Controller
public class MainController {

	@Autowired
	private CredentialsService credentialsService;
	
	
	@GetMapping("/")
	public String getHome() {
		return "index.html";
	}
	
	@GetMapping("/login")
	public String showLogin(@RequestParam(value = "error", required = false) boolean error, Model model) {

	    if (error)
	        model.addAttribute("msgError", "Username o password errati");
	    
		return "login.html";
	}
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "register.html";
	}
	
	@PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                             BindingResult userBindingResult,
                             @Valid @ModelAttribute("credentials") Credentials credentials,
                             @Valid @RequestParam(name = "confirmPwd") String confirmPwd,
                             BindingResult credentialsBindingResult,
                             Model model) {
        
        // Validation check for both objects
        if (userBindingResult.hasErrors() || credentialsBindingResult.hasErrors()) {
            model.addAttribute("msgError", "Errore nella validazione dei dati");
            model.addAttribute("user", user);
            model.addAttribute("credentials", credentials);
            return "register.html";
        }
        // Check if username already exists
        if (credentialsService.existsByUsername(credentials.getUsername())) {
            model.addAttribute("msgError", "Username già in uso");
            model.addAttribute("user", user);
            model.addAttribute("credentials", credentials);
            return "register.html";
        }
        if(!credentials.getPassword().equals(confirmPwd)) {
        	model.addAttribute("msgError", "Le 2 password scritte non sono uguali");
        	model.addAttribute("user", user);
            model.addAttribute("credentials", credentials);
            return "register.html";
        }
        if(credentials.getPassword().length() < 8) {
        	model.addAttribute("msgError", "La password scritta deve avere almeno 8 caratteri");
        	model.addAttribute("user", user);
            model.addAttribute("credentials", credentials);
            return "register.html";
        }
        if(credentials.getUsername().length() < 8) {
        	model.addAttribute("msgError", "L'username scelto deve avere almeno 8 caratteri");
        	model.addAttribute("user", user);
            model.addAttribute("credentials", credentials);
            return "register.html";
        }
        try {
            
            // Link user to credentials
            credentials.setUser(user);
            
            // Save credentials (which will cascade to user due to @OneToOne relationship)
            credentialsService.saveCredentials(credentials);
            
            model.addAttribute("msgSuccess", "Registrazione completata con successo");
            return "redirect:/user/"+ user.getId();
            
        } catch (Exception e) {
            model.addAttribute("msgError", "Errore durante la registrazione");
            return "register.html";
        }
    }
	
}
