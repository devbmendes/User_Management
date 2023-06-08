package com.devb.book_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.devb.book_store.entity.Category;
import com.devb.book_store.repository.CategoryRepository;
import com.devb.book_store.service.exception.DataIntegratyViolationException;
import com.devb.book_store.service.exception.ObjectNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category save(Category category) {
		ifIsPresent(category.getType());
		category.setId(null);
		Category cat = categoryRepository.save(category);
		return cat;
	}

	@Override
	public Category findById(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		return category.orElseThrow(()->
		new ObjectNotFoundException("Category with ID: "+id+" not found"));
	}

	@Override
	public List<Category> getAll() {
		List<Category> category = categoryRepository.findAll();
		return category;
	}

	@Override
	public Category update(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		findById(id);
		try {
			categoryRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Object cannot be deleted : DB Rules ");
		}
		
	}

	@Override
	public Category findByTypeIgnoreCase(String type) {
		Optional<Category> category = categoryRepository.findByTypeIgnoreCase(type);
		return category.orElseThrow(()->
		new ObjectNotFoundException("Category : "+type+" not found"));
	}

	@Override
	public void ifIsPresent(String type) {
		Optional<Category> category = categoryRepository.findByTypeIgnoreCase(type);
		if(category.isPresent()) {
			throw new DataIntegratyViolationException("Category already present in DB");
		}
	}

}
