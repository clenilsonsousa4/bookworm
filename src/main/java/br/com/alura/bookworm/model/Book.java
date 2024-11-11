package br.com.alura.bookworm.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(unique = true, nullable = false)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Author> authors;
    @Column(unique = true, nullable = false)
    private String language;
    @Column(unique = true, nullable = false)
    private Integer downloadCount;

    public Book(BookData book) {
        this.title = book.title();
        this.authors = book.authors().stream().map(Author::new).toList();
        this.downloadCount = book.downloadCount();
        this.language = book.languages().get(0);
    }

    public Book() {}

    @Override
    public String toString() {
        return "Título do Livro: " + title +
                " - Idioma: " + language +
                " - Quantidade de Downloads = " + downloadCount +
                " - Autores: " + authors;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
