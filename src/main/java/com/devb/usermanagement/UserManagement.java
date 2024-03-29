package com.devb.usermanagement;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.devb.usermanagement.UserManagement;
import com.devb.usermanagement.entity.Author;
import com.devb.usermanagement.entity.Category;
import com.devb.usermanagement.entity.Role;
import com.devb.usermanagement.entity.auth.AuthService;
import com.devb.usermanagement.entity.auth.UserApp;
import com.devb.usermanagement.entity.auth.UserAppRepository;
import com.devb.usermanagement.entity.auth.UserRegistrationRequest;
import com.devb.usermanagement.repository.AuthorRepositoy;
import com.devb.usermanagement.repository.CategoryRepository;



@SpringBootApplication
public class UserManagement implements CommandLineRunner {
	

	private final AuthorRepositoy repositoy;
	private final CategoryRepository categoryRepository;
	private final AuthService authService;
	private final UserAppRepository userAppRepository;
	private final PasswordEncoder encoder;
	
	public UserManagement(AuthorRepositoy repositoy, CategoryRepository categoryRepository, AuthService authService,
			UserAppRepository userAppRepository,PasswordEncoder encoder) {
		super();
		this.repositoy = repositoy;
		this.categoryRepository = categoryRepository;
		this.authService = authService;
		this.userAppRepository = userAppRepository;
		this.encoder = encoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserManagement.class, args);
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
		
		authService.save(new UserRegistrationRequest("julio","Correia","julio@gmail.com","Juliocorreia&10","USER"));
		UserApp userApp = new UserApp(null, "Balduino", "Mendes", "baldhuino@gmail.com",
				encoder.encode("Balduino&mendes10"));
		userApp.setRole(Role.ADMIN);
		userAppRepository.save(userApp);
		
	}

}
