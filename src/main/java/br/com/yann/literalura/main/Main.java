package br.com.yann.literalura.main;

import br.com.yann.literalura.model.ApiResponse;
import br.com.yann.literalura.model.Author;
import br.com.yann.literalura.model.Book;
import br.com.yann.literalura.model.DataBook;
import br.com.yann.literalura.repository.AuthorRepository;
import br.com.yann.literalura.repository.BookRepository;
import br.com.yann.literalura.service.ConsumeAPI;
import br.com.yann.literalura.service.ConvertData;

import java.util.List;
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

        var existingAuthor = authorRepository.findByName(dataBook.authors().getFirst().name());
        Author author;
        author = existingAuthor.orElseGet(() -> new Author(dataBook.authors().getFirst()));

        Book book = new Book(dataBook);
        book.setAuthor(author);

        author.addBook(book);

        authorRepository.save(author);
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

    private void listBooks() {
        List<Book> books = bookRepository.findAll();
        if(!books.isEmpty()) {
            books.forEach(System.out::println);
        } else {
            System.out.println("No books registered");
        }
    }

    private void listAuthors() {
        List<Author> authors = authorRepository.findAll();
        if(!authors.isEmpty()) {
            authors.forEach(System.out::println);
        } else {
            System.out.println("No authors registered");
        }
    }

    private void listLivingAuthors() {
        System.out.println("Enter the year for the query (for years before Christ, enter it as negative)");
        var year = sc.nextInt();
        sc.nextLine();
        List<Author> authors = authorRepository.listAuthorsInGivenYear(year);
        if(!authors.isEmpty()) {
            authors.forEach(System.out::println);
        } else {
            System.out.printf("\nNo authors living in %d", year);
        }

    }

    private void listBooksForLanguage() {
        String language = selectLanguage();
        if (language != null) {
            List<Book> books = bookRepository.findByLanguage(language);
            if (books.isEmpty()) {
                System.out.println("No books found for the selected language.");
            } else {
                books.forEach(System.out::println);
            }
        } else {
            System.out.println("Select a valid language.");
        }
    }

    private String selectLanguage() {
        System.out.println("""
                \n==========
                Select the language:
                1- portuguese
                2 - english
                3 - spanish
                4  - french
                """);
        var option = sc.nextInt();
        sc.nextLine();

        return switch (option) {
            case 1 -> "pt";
            case 2 -> "en";
            case 3 -> "es";
            case 4 -> "fr";
            default -> null;
        };
    }
}
