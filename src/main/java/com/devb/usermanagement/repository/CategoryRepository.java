package com.devb.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.devb.usermanagement.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	public Optional<Category> findByTypeIgnoreCase(String type);
}
