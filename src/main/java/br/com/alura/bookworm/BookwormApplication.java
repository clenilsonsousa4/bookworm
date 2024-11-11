package br.com.alura.bookworm;

import br.com.alura.bookworm.service.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookwormApplication implements CommandLineRunner {
	@Autowired
	private Principal principal;

	public static void main(String[] args) {
		SpringApplication.run(BookwormApplication.class, args);
	}

	@Override
	public void run(String... args) {
		principal.showMenu();
	}
}
