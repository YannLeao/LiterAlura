package br.com.yann.literalura.main;

import br.com.yann.literalura.model.ApiResponse;
import br.com.yann.literalura.service.ConsumeAPI;
import br.com.yann.literalura.service.ConvertData;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Scanner;

public class Main {
    private final Scanner sc = new Scanner(System.in);
    ConsumeAPI consume = new ConsumeAPI();
    ConvertData converter = new ConvertData();

    public void start() {
//        var options = """
//                ========== Select one option ==========
//                1 - Search book by tittle
//                2 - List registered books
//                3 - List registered authors
//                4 - List living authors in a given year
//                5 - List books in a given language
//
//                0 - Exit
//                """;
//        int choice;
//        do {
//            System.out.println(options);
//            choice = sc.nextInt();
//            sc.nextLine();
//
//            switch (choice) {
//                case 1:
//                    searchBook();
//                    break;
//                case 2:
//                    listBooks();
//                    break;
//                case 3:
//                    listAuthors();
//                    break;
//                case 4:
//                    listLivingAuthors();
//                    break;
//                case 5:
//                    listBooksForLanguage();
//                    break;
//                case 0:
//                    System.out.println("Exiting...");
//                    break;
//                default:
//                    System.out.println("Unexpected value");
//                    break;
//            }
//
//            System.out.println("\n");
//
//        } while (choice != 0);

        String json = consume.obtainData("https://gutendex.com/books?search=emma");
        System.out.println(json);

//        ApiResponse response = null;
//        try {
//            response = converter.obtainData(json, ApiResponse.class);
//        } catch (JsonProcessingException e) {
//            System.out.println("Not found response");
//        }
//        System.out.println(response.results());
    }

    private void listBooksForLanguage() {
    }

    private void listLivingAuthors() {
    }

    private void listAuthors() {
    }

    private void listBooks() {
    }

    private void searchBook() {
    }
}
