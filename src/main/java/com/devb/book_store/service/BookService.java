package com.devb.book_store.service;


import java.util.List;

import com.devb.book_store.DTO.BookDTO;
import com.devb.book_store.entity.Book;

public interface BookService {
	
	public Book save(BookDTO bookDTO);
	
	public List<Book> getAll();
	
	public Book getById(Integer id);
	
	public Book findByNameIgnoreCase(String name);
	
	public void delete(Integer id);

}
