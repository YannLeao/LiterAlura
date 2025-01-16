package br.com.yann.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name= "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String language;
    private long downloads;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "author_id")
    private Author author;

    public Book() {}

    public Book(DataBook dataBook) {
        title = dataBook.title();
        language = dataBook.languages().getFirst();
        downloads = dataBook.downloads();

        if (!dataBook.authors().isEmpty()) {
            DataAuthor dataAuthor = dataBook.authors().getFirst();
            author = new Author(dataAuthor);
        } else {
            author = null;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getDownloads() {
        return downloads;
    }

    public void setDownloads(long downloads) {
        this.downloads = downloads;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return String.format("""
            \n==========
            Title: %s
            Language: %s
            Downloads: %d
            Author: %s
            ==========
            """,
                title,
                language,
                downloads,
                author != null ? author.getName() : "No author assigned");
    }

}
