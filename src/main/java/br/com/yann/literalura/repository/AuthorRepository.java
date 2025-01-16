package br.com.yann.literalura.repository;

import br.com.yann.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = """
    SELECT * FROM authors
    WHERE (death_year IS NULL OR death_year >= :year)
    AND birth_year <= :year
""", nativeQuery = true)
    List<Author> listAuthorsInGivenYear(int year);

    Optional<Author> findByName(String name);
}
