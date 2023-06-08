package com.devb.book_store.service;

import java.util.List;

import com.devb.book_store.entity.Category;

public interface CategoryService {

	public Category save(Category category);

	public Category findById(Integer id);

	public List<Category> getAll();

	public Category update(Category category);

	public void delete(Integer id);
	
	public Category findByTypeIgnoreCase(String type);
	
	public void ifIsPresent(String type);

}
