package br.com.alura.bookworm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private Integer birthYear;
    @Column(unique = true)
    private Integer deathYear;

    public Author(AuthorData author) {
        this.name = author.name();

        try{
            this.birthYear = Integer.parseInt(author.birthYear());
        } catch (NumberFormatException e) {
            this.birthYear = 0;
        }

        try{
            this.deathYear = Integer.parseInt(author.deathYear());
        } catch (NumberFormatException e) {
            this.deathYear = null;
        }
    }

    public Author() {
    }

    @Override
    public String toString() {
        return "Nome do Autor: " + name +
                " - Ano de Nascimento: " + birthYear +
                " - Ano de Falecimento = " + deathYear;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
