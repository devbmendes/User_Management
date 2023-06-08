package com.devb.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devb.usermanagement.entity.Author;

public interface AuthorRepositoy extends JpaRepository<Author, Integer> {

	public Optional<Author> findByEmailIgnoreCase(String email);
}
