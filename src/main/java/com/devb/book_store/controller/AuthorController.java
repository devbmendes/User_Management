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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devb.book_store.DTO.AuthorDTO;
import com.devb.book_store.entity.Author;
import com.devb.book_store.service.AuthorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/store/author")
@CrossOrigin("**")
public class AuthorController {

	@Autowired
	AuthorService authorService;

	
	@Operation(summary = "GET ALL AUTHOR", description = "Get all Author available",tags = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200")
	})
	@GetMapping("/all")
	public ResponseEntity<List<Author>> findAll() {
		List<Author> listAuthors = authorService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(listAuthors);
	}

	@Operation(summary = "SAVE AUTHOR", description = "Saving a particular Author",tags = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201")
	})
	@PostMapping
	public ResponseEntity<Author> save(@Valid @RequestBody AuthorDTO authorDTO) {
		Author author = authorService.save(authorDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(author);
	}
	@Operation(summary = "GET AUTHOR BY ID", description = "Getting a particular Author by ID",tags = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Author> getById(@PathVariable Integer id) {
		Author author = authorService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(author);
	}

	@Operation(summary = "UPDATE AUTHOR", description = "Update a particular Author",tags = "PUT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Author> update(@PathVariable Integer id,@Valid @RequestBody AuthorDTO authorDTO) {
		Author author = authorService.update(id, authorDTO);
		return ResponseEntity.status(HttpStatus.OK).body(author);
	}

	@Operation(summary = "GET AUTHOR BY EMAIL", description = "Get a particular Author by EMAIL like a RequestParam",tags = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200")
	})
	@GetMapping
	public ResponseEntity<Author> findByEMail(@RequestParam String email) {
		Author author = authorService.findByEmail(email);
		return ResponseEntity.status(HttpStatus.OK).body(author);
	}
	
	@Operation(summary = "DELETE AUTHOR BY ID", description = "Delete a particular Author by ID",tags = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Author> delete(@PathVariable Integer id){
		authorService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
