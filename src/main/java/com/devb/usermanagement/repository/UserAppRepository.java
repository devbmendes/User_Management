package com.devb.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devb.usermanagement.entity.UserApp;

public interface UserAppRepository extends JpaRepository<UserApp, Integer>{
	
	Optional<UserApp> findByEmail(String email);

}
