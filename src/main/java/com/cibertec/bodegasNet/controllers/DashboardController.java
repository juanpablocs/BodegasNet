package com.cibertec.bodegasNet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@GetMapping("")
	public String showDashboard(Model model) {
		model.addAttribute("title", "Página Dashboard");
		return "dashboard";
	}
}
