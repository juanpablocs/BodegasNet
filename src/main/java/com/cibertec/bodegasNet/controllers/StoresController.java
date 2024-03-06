package com.cibertec.bodegasNet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stores")
public class StoresController {
	
	@GetMapping("")
	public String showStores(Model model) {
		model.addAttribute("title", "Página Stores");
		return "stores";
	}

}
