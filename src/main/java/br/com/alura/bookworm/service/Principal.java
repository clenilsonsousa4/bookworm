package br.com.alura.bookworm.service;

import br.com.alura.bookworm.enums.LanguageEnum;
import br.com.alura.bookworm.model.Author;
import br.com.alura.bookworm.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class Principal {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        int option = -1;
        while (option != 0) {
            var menu = """
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    
                    0 - Sair
                    """;

            System.out.println();
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    getAllBooks();
                    break;
                case 3:
                    searchAllAuthors();
                    break;
                case 4:
                    findAuthorsAliveOnCertainYear();
                    break;
                case 5:
                    findBooksWithCertainLanguage();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void searchBookByTitle() {
        System.out.println("Digite o título do livro:");
        var title = scanner.nextLine();

        try{
            System.out.println(bookService.searchBookByTitle(title));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAllBooks() {
        List<Book> books = bookService.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("Não há livros cadastrados");
        } else {
            System.out.println("\nLivros Encontrados:");
            books.forEach(System.out::println);
        }
    }

    private void searchAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        if (authors.isEmpty()) {
            System.out.println("Não há autores cadastrados");
        } else {
            System.out.println("\nAutores Encontrados:");
            authorService.getAllAuthors().forEach(System.out::println);
        }
    }

    private void findAuthorsAliveOnCertainYear() {
        System.out.println("Digite o ano que quer checar os autores que estavam vivos:");
        var deathYear = scanner.nextLine();

        List<Author> authorsAlive = authorService.findAuthorsAliveOnCertainYear(deathYear);
        if (authorsAlive.isEmpty()) {
            System.out.println("Não havia autores vivos nesse ano");
        } else {
            System.out.println("\nAutores Vivos Encontrados no Ano " + deathYear + ":");
            authorsAlive.forEach(System.out::println);
        }
    }

    private void findBooksWithCertainLanguage() {
        LanguageEnum languageEnum = null;

        while (languageEnum == null) {
            var languageOptions = """
                        Digite a sigla do idioma do livro que quer buscar:
                        pt - Português;
                        en - Inglês;
                        """;
            System.out.println(languageOptions);

            var languageSelected = scanner.nextLine();

            languageEnum = LanguageEnum.fromString(languageSelected);
        }

        List<Book> booksFound = bookService.findBooksWithCertainLanguage(languageEnum.getLanguage());
        if (booksFound.isEmpty()) {
            System.out.println("Não foram encontrados livros no idioma selecionado");
        } else {
            System.out.println("\nLivros Encontrados em " + languageEnum.getDescription() + ":");
            booksFound.forEach(System.out::println);
        }
    }
}
