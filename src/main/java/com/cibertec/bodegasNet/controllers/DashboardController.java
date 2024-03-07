package com.cibertec.bodegasNet.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cibertec.bodegasNet.models.Category;
import com.cibertec.bodegasNet.models.Merchant;
import com.cibertec.bodegasNet.models.Product;
import com.cibertec.bodegasNet.models.User;
import com.cibertec.bodegasNet.services.CategoryService;
import com.cibertec.bodegasNet.services.MerchantService;
import com.cibertec.bodegasNet.services.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
    private MerchantService merchantService;
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;
	
    @Autowired
    private HttpSession session; // Asumiendo que tienes acceso a HttpSession

    
	@GetMapping("")
	public String showDashboard(Model model) {
		model.addAttribute("title", "Página Dashboard");
		
		Long userId = (Long) session.getAttribute("userId"); // Asume que el ID del usuario está almacenado en la sesión
        Merchant merchant = merchantService.findMerchantByUserId(userId).orElse(null);
        List<Product> products = productService.findMerchantByUserId(merchant.getId());
        model.addAttribute("merchant", merchant);
        model.addAttribute("products", products);
		return "dashboard";
	}
	
	@GetMapping("/products")
	public String showProducts(Model model) {
		model.addAttribute("title", "Página Dashboard");
		
		Long userId = (Long) session.getAttribute("userId"); // Asume que el ID del usuario está almacenado en la sesión
        Merchant merchant = merchantService.findMerchantByUserId(userId).orElse(null);

        model.addAttribute("merchant", merchant);
        
		return "dashboard";
	}
	
	@GetMapping("/products/create")
	public String showProductsCreate(Model model) {
		model.addAttribute("title", "Página Form Producto");
		
		Long userId = (Long) session.getAttribute("userId"); // Asume que el ID del usuario está almacenado en la sesión
        Merchant merchant = merchantService.findMerchantByUserId(userId).orElse(null);

        model.addAttribute("merchant", merchant);
        model.addAttribute("categories", categoryService.findAll());
        
		return "dashboard_products_form";
	}
	
	@PostMapping("/products/create")
    public String createProduct(@RequestParam("name") String name,
                                @RequestParam("price") Integer price,
                                @RequestParam("image") MultipartFile image,
                                @RequestParam("category") Long categoryId,
                                HttpSession session) throws IOException {
        Long userId = (Long) session.getAttribute("userId");
        Merchant merchant = merchantService.findMerchantByUserId(userId).orElseThrow(() -> new RuntimeException("Merchant not found"));
        Optional<Category> category = categoryService.findById(categoryId);
        
        String imageName = productService.saveImage(image);
        
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setPhoto(imageName);
        product.setMerchant(merchant);
         product.setCategory(category.get());
        
        productService.save(product); // Asumiendo que tu servicio puede manejar la carga de imágenes

        return "redirect:/dashboard/products";
    }
	
	@PostMapping("/createMerchant")
    public String createMerchant(@RequestParam("merchantName") String merchantName,
                                 @RequestParam("description") String description,
                                 @RequestParam("image") MultipartFile image,
                                 HttpSession session) throws IOException {
        Long userId = (Long) session.getAttribute("userId");
        String imageName = merchantService.saveImage(image); // Implementa este método para guardar la imagen

        Merchant merchant = new Merchant();
        User user = new User();
        user.setId(userId);
        merchant.setUser(user);
        merchant.setMerchantName(merchantName);
        merchant.setDescription(description);
        merchant.setImage(imageName);
        merchantService.save(merchant);

        return "redirect:/dashboard";
    }
	 
}

