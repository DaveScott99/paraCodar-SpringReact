package com.astro.paraCodar.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.astro.paraCodar.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmailAndPassword(String email, String password); 
	User findByEmail(String email);
	User findByUsername(String username);
	
	@Query(value = "SELECT * FROM SOCIALCODE.USER WHERE USERNAME LIKE :username%", nativeQuery = true)
	Page<User> searchUsers(Pageable pageable, String username);
	
}
