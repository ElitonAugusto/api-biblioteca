package br.com.biblioteca.repositories;

import br.com.biblioteca.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
