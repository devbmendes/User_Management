package com.devb.book_store;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devb.book_store.entity.Author;
import com.devb.book_store.entity.Category;
import com.devb.book_store.repository.AuthorRepositoy;
import com.devb.book_store.repository.CategoryRepository;

@SpringBootApplication
public class BookStoreApplication implements CommandLineRunner {
	

	private final AuthorRepositoy repositoy;
	private final CategoryRepository categoryRepository;
	
	public BookStoreApplication(AuthorRepositoy repositoy, CategoryRepository categoryRepository) {
		super();
		this.repositoy = repositoy;
		this.categoryRepository = categoryRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Author author1 = new Author(null, "Balduino", "Mendes", "baldhuino@gmail.com");
		Author author2 = new Author(null, "Helton", "Soares", "helton@gmail.com");
		repositoy.save(author1);
		repositoy.save(author2);
		
		
		Category category1 = new Category(null, "ADVENTURE_STORIES", "Adventure novels whisk readers off to faraway lands. Unlike fantasy ");
		
		Category category2 = new Category(null,"CLASSICS","Classics encompass a range of genres — but they always stand the test of time. ");
		
		Category category3 = new Category(null,"CRIMES","From murder mysteries to true crime stories, crime is an enduringly popular genre. It tells terrifying stories of wrongdoing, and the search for justice.");
		
		Category category4 = new Category(null,"FANTASY","Fantasy books are probably the most popular modern book genre. "
				+ "Thanks to series like Harry Potter and Percy Jackson, it’s particularly popular with young adult readers");
		
		categoryRepository.save(category1);
		categoryRepository.save(category2);
		categoryRepository.save(category3);
		categoryRepository.save(category4);
	}

}
