package br.com.yann.literalura.repository;

import br.com.yann.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
