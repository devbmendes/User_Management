package com.devb.usermanagement.service;


import java.util.List;

import com.devb.usermanagement.DTO.BookDTO;
import com.devb.usermanagement.entity.Book;

public interface BookService {
	
	public Book save(BookDTO bookDTO);
	
	public List<Book> getAll();
	
	public Book getById(Integer id);
	
	public Book findByNameIgnoreCase(String name);
	
	public void delete(Integer id);

}
