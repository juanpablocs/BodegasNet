package com.cibertec.bodegasNet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.bodegasNet.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
