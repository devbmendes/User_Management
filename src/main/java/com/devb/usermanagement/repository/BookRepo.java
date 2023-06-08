package com.devb.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devb.usermanagement.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {
	
	public Optional<Book> findByNameIgnoreCase(String nome);

}
