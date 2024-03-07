package com.cibertec.bodegasNet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cibertec.bodegasNet.models.Merchant;
import com.cibertec.bodegasNet.repository.MerchantRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class MerchantService {

	@Autowired
	private Environment env;
	
    @Autowired
    private MerchantRepository merchantRepository;

    public Optional<Merchant> findMerchantByUserId(Long userId) {
        return merchantRepository.findByUserId(userId);
    }
    
    public Merchant save(Merchant merchant) {
        return merchantRepository.save(merchant);
    }
    
    public List<Merchant> findAll() {
        return merchantRepository.findAll();
    }
    
    public String saveImage(MultipartFile file) throws IOException {
    	String uploadDir = env.getProperty("app.upload.dir");
        Path uploadPath = Paths.get(uploadDir + "/merchants");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadPath + "/" + file.getOriginalFilename());
            Files.write(path, bytes);
            return "merchants/" + file.getOriginalFilename();
        }
        return "default.png";
    }

	public Optional<Merchant> findById(Long merchantId) {
		// TODO Auto-generated method stub
		return merchantRepository.findById(merchantId);
	}
}