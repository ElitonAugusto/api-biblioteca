package br.com.biblioteca.unittests.mockito.services;

import br.com.biblioteca.DTO.PersonDTO;
import br.com.biblioteca.exceptions.RequiredObjectIsNullException;
import br.com.biblioteca.models.Person;
import br.com.biblioteca.repositories.PersonRepository;
import br.com.biblioteca.services.PersonService;
import br.com.biblioteca.unittests.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindyById() {
        Person entity  = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1l)).thenReturn(Optional.of(entity));

        var result = service.findyById(1l);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</persons/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testFindAll() {
        List<Person> list  = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();
        assertNotNull(people);
        assertEquals(14, people.size());

        var person1 = people.get(1);
        assertNotNull(person1);
        assertNotNull(person1.getKey());
        assertNotNull(person1.getLinks());
        assertTrue(person1.toString().contains("links: [</persons/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", person1.getFirstName());
        assertEquals("Last Name Test1", person1.getLastName());
        assertEquals("Address Test1", person1.getAddress());
        assertEquals("Female", person1.getGender());

        var person4 = people.get(4);
        assertNotNull(person4);
        assertNotNull(person4.getKey());
        assertNotNull(person4.getLinks());
        assertTrue(person4.toString().contains("links: [</persons/4>;rel=\"self\"]"));
        assertEquals("First Name Test4", person4.getFirstName());
        assertEquals("Last Name Test4", person4.getLastName());
        assertEquals("Address Test4", person4.getAddress());
        assertEquals("Male", person4.getGender());

        var person9 = people.get(9);
        assertNotNull(person9);
        assertNotNull(person9.getKey());
        assertNotNull(person9.getLinks());
        assertTrue(person9.toString().contains("links: [</persons/9>;rel=\"self\"]"));
        assertEquals("First Name Test9", person9.getFirstName());
        assertEquals("Last Name Test9", person9.getLastName());
        assertEquals("Address Test9", person9.getAddress());
        assertEquals("Female", person9.getGender());
    }

    @Test
    void testCreate() {
        Person entity  = input.mockEntity(1);
        entity.setId(1l);

        Person persisted = entity;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);
        dto.setKey(1l);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(dto);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</persons/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());
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
        Person entity  = input.mockEntity(1);
        Person persisted = entity;
        persisted.setId(1L);
        PersonDTO dto = input.mockDTO(1);
        dto.setKey(1l);

        when(repository.findById(1l)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(dto);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</persons/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testDelete() {
        Person entity  = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1l)).thenReturn(Optional.of(entity));
        service.delete(1l);
    }
}