package br.com.yann.literalura;

import br.com.yann.literalura.main.Main;
import br.com.yann.literalura.repository.AuthorRepository;
import br.com.yann.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(authorRepository, bookRepository);
		main.start();

	}
}
