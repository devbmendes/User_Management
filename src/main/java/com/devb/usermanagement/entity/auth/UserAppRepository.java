package com.devb.usermanagement.entity.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAppRepository extends JpaRepository<UserApp, Integer>{
	
	Optional<UserApp> findByEmail(String email);
	Optional<UserApp> findByPassw(String passw);

}
