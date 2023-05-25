package br.com.biblioteca.unittests.mockito.services;

import br.com.biblioteca.DTO.BookDTO;
import br.com.biblioteca.exceptions.RequiredObjectIsNullException;
import br.com.biblioteca.models.Book;
import br.com.biblioteca.repositories.BookRepository;
import br.com.biblioteca.services.BookService;
import br.com.biblioteca.unittests.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    MockBook input;

    @InjectMocks
    private BookService service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindyById() {
        Book entity  = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1l)).thenReturn(Optional.of(entity));

        var result = service.findyById(1l);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</books/1>;rel=\"self\"]"));

        assertEquals("Author Test1", result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(new BigDecimal(1), result.getPrice());
        assertEquals("Title Test1", result.getTitle());
    }

    @Test
    void testFindAll() {
        List<Book> list  = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);

        var books = service.findAll();
        assertNotNull(books);
        assertEquals(14, books.size());

        var book1 = books.get(1);
        assertNotNull(book1);
        assertNotNull(book1.getKey());
        assertNotNull(book1.getLinks());
        assertTrue(book1.toString().contains("links: [</books/1>;rel=\"self\"]"));
        assertEquals("Author Test1", book1.getAuthor());
        assertNotNull(book1.getLaunchDate());
        assertEquals(new BigDecimal(1), book1.getPrice());
        assertEquals("Title Test1", book1.getTitle());

        var book4 = books.get(4);
        assertNotNull(book4);
        assertNotNull(book4.getKey());
        assertNotNull(book4.getLinks());
        assertTrue(book4.toString().contains("links: [</books/4>;rel=\"self\"]"));
        assertEquals("Author Test4", book4.getAuthor());
        assertNotNull(book4.getLaunchDate());
        assertEquals(new BigDecimal(4), book4.getPrice());
        assertEquals("Title Test4", book4.getTitle());

        var book9 = books.get(9);
        assertNotNull(book9);
        assertNotNull(book9.getKey());
        assertNotNull(book9.getLinks());
        assertTrue(book9.toString().contains("links: [</books/9>;rel=\"self\"]"));
        assertEquals("Author Test9", book9.getAuthor());
        assertNotNull(book9.getLaunchDate());
        assertEquals(new BigDecimal(9), book9.getPrice());
        assertEquals("Title Test9", book9.getTitle());;
    }

    @Test
    void testCreate() {
        Book entity  = input.mockEntity(1);
        entity.setId(1l);

        Book persisted = entity;
        persisted.setId(1L);

        BookDTO dto = input.mockDTO(1);
        dto.setKey(1l);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(dto);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</books/1>;rel=\"self\"]"));
        assertEquals("Author Test1", result.getAuthor());
        assertNotNull(result.getLaunchDate());;
        assertEquals(new BigDecimal(1), result.getPrice());
        assertEquals("Title Test1", result.getTitle());
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () ->{
            service.create(null);
        } );

        String expectedMessage = "Atenção: Não é permitodo salvar um objeto nulo!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () ->{
            service.update(null);
        } );

        String expectedMessage = "Atenção: Não é permitodo salvar um objeto nulo!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdate() {
        Book entity  = input.mockEntity(1);
        Book persisted = entity;
        persisted.setId(1L);
        BookDTO dto = input.mockDTO(1);
        dto.setKey(1l);

        when(repository.findById(1l)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(dto);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</books/1>;rel=\"self\"]"));
        assertEquals("Author Test1", result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(new BigDecimal(1), result.getPrice());
        assertEquals("Title Test1", result.getTitle());
    }

    @Test
    void testDelete() {
        Book entity  = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1l)).thenReturn(Optional.of(entity));
        service.delete(1l);
    }
}