package com.devb.book_store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devb.book_store.entity.Author;

public interface AuthorRepositoy extends JpaRepository<Author, Integer> {

	public Optional<Author> findByEmailIgnoreCase(String email);
}
