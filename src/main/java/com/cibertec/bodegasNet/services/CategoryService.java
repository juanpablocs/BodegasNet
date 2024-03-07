package com.cibertec.bodegasNet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.bodegasNet.models.Category;
import com.cibertec.bodegasNet.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> findAll() {
        return categoryRepository.findAll();
    }
	
	public Optional<Category> findById(Long id) {
		return categoryRepository.findById(id);
	}
}
