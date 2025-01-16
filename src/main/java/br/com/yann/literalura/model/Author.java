package br.com.yann.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
    public class Author {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(unique = true)
        private String name;
        private int birthYear;
        private int deathYear;
        @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private List<Book> books = new ArrayList<>();

    public Author() {}

    public Author(DataAuthor dataAuthor) {
        name = dataAuthor.name();
        birthYear = dataAuthor.birthYear();
        deathYear = dataAuthor.deathYear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        StringBuilder booksList = new StringBuilder();
        if (books != null) {
            for (Book book : books) {
                booksList.append("- ").append(book.getTitle()).append("\n");
            }
        } else {
            booksList.append("No books available\n");
        }

        return String.format("""
            \n==========
            Author: %s
            Birth year: %d
            Death year: %d
            Books:
            %s
            ==========
            """,
                name, birthYear, deathYear, booksList.toString());
    }

    public void addBook(Book book) {
        books.add(book);
        if (book.getAuthor() != this) {
            book.setAuthor(this);
        }
    }
}
