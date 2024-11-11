package br.com.alura.bookworm.repository;

import br.com.alura.bookworm.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String bookTitle);

    List<Book> findByLanguage(String language);
}
