package br.com.yann.literalura.main;

import br.com.yann.literalura.model.ApiResponse;
import br.com.yann.literalura.model.Book;
import br.com.yann.literalura.model.DataBook;
import br.com.yann.literalura.repository.AuthorRepository;
import br.com.yann.literalura.repository.BookRepository;
import br.com.yann.literalura.service.ConsumeAPI;
import br.com.yann.literalura.service.ConvertData;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    private final Scanner sc = new Scanner(System.in);
    private final String ADDRESS = "https://gutendex.com/books?search=";
    private final ConsumeAPI consume = new ConsumeAPI();
    private final ConvertData converter = new ConvertData();

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public Main(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void start() {
        var options = """
                ========== Select one option ==========
                1 - Search book by tittle
                2 - List registered books
                3 - List registered authors
                4 - List living authors in a given year
                5 - List books in a given language

                0 - Exit
                """;
        int choice;
        do {
            System.out.println(options);
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    searchBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    listAuthors();
                    break;
                case 4:
                    listLivingAuthors();
                    break;
                case 5:
                    listBooksForLanguage();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Unexpected value");
                    break;
            }

            System.out.println("\n");

        } while (choice != 0);
    }

    private void searchBook() {
        DataBook dataBook = getBook();
        Book book = new Book(dataBook);
        bookRepository.save(book);
        System.out.println(book);
    }

    private DataBook getBook() {
        System.out.println("Type the book's title:");
        var title = sc.nextLine();
        var json = consume.obtainData(ADDRESS + title.toLowerCase().replace(" ", "+"));
        ApiResponse response = converter.obtainData(json, ApiResponse.class);
        Optional<DataBook> data = response.results().stream()
                .filter(book -> book.title().equalsIgnoreCase(title))
                .findFirst();
        return data.orElse(null);
    }

    private void listBooksForLanguage() {
    }

    private void listLivingAuthors() {
    }

    private void listAuthors() {
    }

    private void listBooks() {
    }

}
