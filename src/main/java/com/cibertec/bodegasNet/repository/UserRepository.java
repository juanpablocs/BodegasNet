package com.cibertec.bodegasNet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.bodegasNet.models.User;

import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}