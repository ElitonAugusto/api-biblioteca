package br.com.biblioteca.repositories;

import br.com.biblioteca.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
