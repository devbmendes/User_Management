package com.devb.book_store.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.devb.book_store.DTO.BookDTO;
import com.devb.book_store.entity.Author;
import com.devb.book_store.entity.Book;
import com.devb.book_store.entity.Category;
import com.devb.book_store.repository.BookRepo;
import com.devb.book_store.service.exception.ObjectNotFoundException;


@Service
public class BookServiceImpl  implements BookService{
	
	private final BookRepo bookRepo;
	private final CategoryService categoryService;
	private final AuthorService authorService;


	public BookServiceImpl(BookRepo bookRepo, CategoryService categoryService, AuthorService authorService) {
		super();
		this.bookRepo = bookRepo;
		this.categoryService = categoryService;
		this.authorService = authorService;
	}

	public String randon() {
		Random random = new Random();
		
		Long codeLong = random.nextLong(30000, 6000000);
		return codeLong.toString();
	}
	
	@Override
	public Book save(BookDTO bookDTO) {
		Category category = categoryService.findById(bookDTO.getCategory_id());
		Book book = new Book();
		book.setCategory(category);
		List<Author> listAuthor = new ArrayList<>();
		for(int x: bookDTO.getAuthor_id()) {
			Author author =authorService.findById(x);
			listAuthor.add(author);
	  }
        book.setAuthors(listAuthor);
		book.setName(bookDTO.getNome());
		book.setDesc(bookDTO.getDesc());
		book.setRef(book.getName().replaceAll("\\s", "")+ randon()+LocalDate.now().toString());
		return bookRepo.save(book);
	}

	@Override
	public List<Book> getAll() {
		List<Book> listBooks = bookRepo.findAll();
		
		return listBooks;
	}

	@Override
	public Book getById(Integer id) {
		Optional<Book> book = bookRepo.findById(id);
		return book.orElseThrow(()->
		new ObjectNotFoundException("Book with ID: "+id+" not found"));
	}

	@Override
	public Book findByNameIgnoreCase(String name) {
		Optional<Book> book = bookRepo.findByNameIgnoreCase(name);
		return book.orElseThrow(()->
		new ObjectNotFoundException("Book with Name: "+name+" not found"));
	}

	@Override
	public void delete(Integer id) {
		getById(id);
		bookRepo.deleteById(id);
	}

}
