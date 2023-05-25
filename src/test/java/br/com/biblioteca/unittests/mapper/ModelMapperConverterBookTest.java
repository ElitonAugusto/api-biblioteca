package br.com.biblioteca.unittests.mapper;

import br.com.biblioteca.DTO.BookDTO;
import br.com.biblioteca.mapper.BookMapper;
import br.com.biblioteca.models.Book;
import br.com.biblioteca.unittests.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ModelMapperConverterBookTest {
    MockBook inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockBook();
    }

    @Test
    public void parseEntityToDTOTest() {
        BookDTO output = BookMapper.parseObject(inputObject.mockEntity(), BookDTO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Author Test0", output.getAuthor());
        assertNotNull(output.getLaunchDate());
        assertEquals(new BigDecimal(0), output.getPrice());
        assertEquals("Title Test0", output.getTitle());
    }

    @Test
    public void parseEntityListToDTOListTest() {
        List<BookDTO> outputList = BookMapper.parseListObjects(inputObject.mockEntityList(), BookDTO.class);
        BookDTO outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("Author Test0", outputZero.getAuthor());
        assertNotNull(outputZero.getLaunchDate());
        assertEquals(new BigDecimal(0), outputZero.getPrice());
        assertEquals("Title Test0", outputZero.getTitle());

        BookDTO outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getKey());
        assertEquals("Author Test7", outputSeven.getAuthor());
        assertNotNull(outputSeven.getLaunchDate());
        assertEquals(new BigDecimal(7), outputSeven.getPrice());
        assertEquals("Title Test7", outputSeven.getTitle());

        BookDTO outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        assertEquals("Author Test12", outputTwelve.getAuthor());
        assertNotNull(outputTwelve.getLaunchDate());
        assertEquals(new BigDecimal(12), outputTwelve.getPrice());
        assertEquals("Title Test12", outputTwelve.getTitle());
    }

    @Test
    public void parseDTOToEntityTest() {
        Book output = BookMapper.parseObject(inputObject.mockDTO(), Book.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Author Test0", output.getAuthor());
        assertNotNull(output.getLaunchDate());
        assertEquals(new BigDecimal(0), output.getPrice());
        assertEquals("Title Test0", output.getTitle());
    }

    @Test
    public void parserDTOListToEntityListTest() {
        List<Book> outputList = BookMapper.parseListObjects(inputObject.mockDTOList(), Book.class);
        Book outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Author Test0", outputZero.getAuthor());
        assertNotNull(outputZero.getLaunchDate());
        assertEquals(new BigDecimal(0), outputZero.getPrice());
        assertEquals("Title Test0", outputZero.getTitle());

        Book outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Author Test7", outputSeven.getAuthor());
        assertNotNull(outputSeven.getLaunchDate());
        assertEquals(new BigDecimal(7), outputSeven.getPrice());
        assertEquals("Title Test7", outputSeven.getTitle());

        Book outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Author Test12", outputTwelve.getAuthor());
        assertNotNull(outputTwelve.getLaunchDate());
        assertEquals(new BigDecimal(12), outputTwelve.getPrice());
        assertEquals("Title Test12", outputTwelve.getTitle());
    }
}
