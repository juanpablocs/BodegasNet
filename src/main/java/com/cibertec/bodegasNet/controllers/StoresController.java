package com.cibertec.bodegasNet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.bodegasNet.models.Merchant;
import com.cibertec.bodegasNet.services.MerchantService;

@Controller
@RequestMapping("/stores")
public class StoresController {
	
	@Autowired
    private MerchantService merchantService;
	
	@GetMapping("")
	public String showStores(Model model) {
		model.addAttribute("title", "Página Stores");
		model.addAttribute("merchants", merchantService.findAll());
		return "stores";
	}

	@GetMapping("/{merchantId}")
	public String showMerchantProducts(@PathVariable Long merchantId, Model model) {
	    Merchant merchant = merchantService.findById(merchantId)
	        .orElseThrow(() -> new IllegalArgumentException("Invalid merchant Id:" + merchantId));
	    model.addAttribute("title", "Productos de la Tienda");
	    model.addAttribute("products", merchant.getProducts());
	    return "stores_products";
	}
}
