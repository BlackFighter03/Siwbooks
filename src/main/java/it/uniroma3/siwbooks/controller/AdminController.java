package it.uniroma3.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static it.uniroma3.siwbooks.model.Credentials.ADMIN_ROLE;

import it.uniroma3.siwbooks.model.Credentials;
import it.uniroma3.siwbooks.model.User;
import it.uniroma3.siwbooks.service.CredentialsService;
import it.uniroma3.siwbooks.service.UserService;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	private boolean verifyAdmin(Long idUrl, User user) {
		return user!= null && idUrl == user.getId() && this.credentialsService.getCredentialsByUser(user).getRole().equals(ADMIN_ROLE);
	}
	
	@GetMapping("/{id}")
	public String getMethodName(@PathVariable("id") Long id, 
			@RequestParam(value = "showPasswordModal", required = false, defaultValue = "false") boolean showPasswordModal,
			Model model) {
		User user = userService.getCurrentUser();
		if(!verifyAdmin(id, user))
			return "redirect:/login";
		model.addAttribute("user", user);
		model.addAttribute("showPasswordModal", showPasswordModal);
		return "admin/profile.html";
	}
	
	@GetMapping("/{id}/modifyUser")
	public String showFormUpdateInfo(@PathVariable("id") Long id, Model model) {
		if (!verifyAdmin(id, userService.getCurrentUser()))
			return "redirect:/login";
		model.addAttribute("user", userService.findById(id));
		return "admin/formModifyUser.html";
	}
	
	@PostMapping("/{id}/modifyUser")
	public String updateInfo(@PathVariable("id") Long id, @ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())
			return "admin/formModifyUser.html";
		if (!verifyAdmin(id, userService.getCurrentUser()))
			return "redirect:/login";
		this.userService.saveUser(user);
		return "redirect:/admin/" + user.getId();
	}
	
	@PostMapping("/{id}/changePassword")
	public String updateCredentials(@PathVariable("id") Long id, @RequestParam @Valid String confirmPwd, @RequestParam @Valid String newPwd, Model model) {
		if(newPwd == null || confirmPwd == null || newPwd.equals("") || confirmPwd.equals("") || !newPwd.equals(confirmPwd)) {
			model.addAttribute("msgError", "Il campo della nuova password è vuota");
			return "admin/profile.html";
		}
		User user = userService.getCurrentUser();
		if (!verifyAdmin(id, user))
			return "redirect:/login";
		Credentials credentials = this.credentialsService.getCredentialsByUser(user);
		this.credentialsService.saveCredentials(credentials);
		return "redirect:/admin/" + user.getId();
	}
	
}
