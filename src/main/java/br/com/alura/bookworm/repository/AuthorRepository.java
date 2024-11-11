package br.com.alura.bookworm.repository;

import br.com.alura.bookworm.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a WHERE a.deathYear > :deathYear AND a.birthYear <= :deathYear")
    List<Author> findAuthorsAliveOnCertainYear(String deathYear);
}
