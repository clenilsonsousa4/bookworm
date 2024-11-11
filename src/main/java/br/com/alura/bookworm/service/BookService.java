package br.com.alura.bookworm.service;

import br.com.alura.bookworm.model.Book;
import br.com.alura.bookworm.model.BookData;
import br.com.alura.bookworm.repository.BookRepository;
import br.com.alura.bookworm.utils.DataConverter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private GutendexAPIService gutendexAPIService;
    @Autowired
    private BookRepository bookRepository;

    public Book searchBookByTitle(String bookTitle) throws RuntimeException {
        Optional<Book> bookOptional = bookRepository.findByTitle(bookTitle);
        if (bookOptional.isPresent()) {
            return bookOptional.get();
        } else {
            try {
                String json = new JSONObject(gutendexAPIService.searchBookByTitle(bookTitle)).getJSONArray("results").get(0).toString();
                BookData bookData = DataConverter.convertObject(json, BookData.class);
                return bookRepository.save(new Book(bookData));
            } catch (JSONException e) {
                throw new RuntimeException("Não Foram Encontrados Livros com Esse Título");
            }
        }
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findBooksWithCertainLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }
}
