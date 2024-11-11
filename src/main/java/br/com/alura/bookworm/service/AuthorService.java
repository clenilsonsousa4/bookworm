package br.com.alura.bookworm.service;

import br.com.alura.bookworm.model.Author;
import br.com.alura.bookworm.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> findAuthorsAliveOnCertainYear(String deathYear) {
        return authorRepository.findAuthorsAliveOnCertainYear(deathYear);
    }
}
