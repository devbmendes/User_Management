package com.devb.book_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devb.book_store.entity.Category;
import com.devb.book_store.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("**")
@RequestMapping("/v1/store/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@Operation(summary = "SAVE CATEGORY", description = "Saving a Particular category",tags = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201")
	})
	@PostMapping
	public ResponseEntity<Category> save(@Valid @RequestBody Category category) {
		Category ctg = categoryService.save(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(ctg);
	}

	@Operation(summary = "GET ALL CATEGORY", description = "Getting all the category available",tags = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200")
	})
	@GetMapping("/all")
	public ResponseEntity<List<Category>> findAll() {
		List<Category> categories = categoryService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(categories);
	}

	@Operation(summary = "GET CATEGORY BY ID", description = "Get  a particular category by ID",tags = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Integer id) {
		Category category = categoryService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(category);
	}

	@Operation(summary = "DELETE CATEGORY BY ID", description = "DELETE a Particular category by ID",tags = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Category> deleteById(@PathVariable Integer id) {
		categoryService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	@Operation(summary = "GET CATEGORY BY NAME", description = "Find a Particular category by NAME like a RequestParam",tags = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200")
	})
	@GetMapping
	public ResponseEntity<Category> findByname(@RequestParam String name){
		Category category = categoryService.findByTypeIgnoreCase(name);
		return ResponseEntity.status(HttpStatus.OK).body(category);
	}

}
