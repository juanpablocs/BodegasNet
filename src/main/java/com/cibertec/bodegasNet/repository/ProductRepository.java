package com.cibertec.bodegasNet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.bodegasNet.models.Product;

public interface ProductRepository  extends JpaRepository<Product, Long> {

	List<Product> findByMerchantId(Long merchantId);
}
