package com.devb.usermanagement.controller;

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

import com.devb.usermanagement.DTO.BookDTO;
import com.devb.usermanagement.entity.Book;
import com.devb.usermanagement.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/v1/store/book")
@CrossOrigin("**")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Operation(summary = "SAVE BOOK", description = "Saving a Particular Book",tags = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201")
	})
	@PostMapping
	public ResponseEntity<Book> save(@RequestBody BookDTO bookDTO) {
		Book book = bookService.save(bookDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(book);
	}
	@Operation(summary = "GET ALL BOOK", description = "Getting all Book available",tags = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200")
	})
	@GetMapping("/all")
	public ResponseEntity<List<Book>> getAll(){
		List<Book> lisBooks = bookService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(lisBooks);
	}
	@Operation(summary = "GET A PARTICULAR BOOK BY ID", description = "Get a particular Book by ID",tags = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Book> findById(@PathVariable Integer id){
		Book book = bookService.getById(id);
		return ResponseEntity.status(HttpStatus.OK).body(book);
	}
	@Operation(summary = "GET BOOK BY NAME", description = "Get a particular Book by NAME like a RequestParam",tags = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200")
	})
	@GetMapping
	public ResponseEntity<Book> findByName(@RequestParam(value = "name") String name){
		Book book = bookService.findByNameIgnoreCase(name);
		return ResponseEntity.status(HttpStatus.OK).body(book);
	}
	
	@Operation(summary = "DELETE BOOK BY ID", description = "Delete a particular Book by ID",tags = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		bookService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
