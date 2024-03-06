package com.cibertec.bodegasNet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@GetMapping("/login")
	public String showLogin(Model model) {
		model.addAttribute("title", "Página de Login");
		return "login";
	}
}
