package br.com.biblioteca.mapper;

import br.com.biblioteca.DTO.BookDTO;
import br.com.biblioteca.models.Book;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    private static ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        configureIdBookAndBookDTO();
    }

    private static void configureIdBookAndBookDTO() {
        TypeMap<Book, BookDTO> typeMap = mapper.createTypeMap(Book.class, BookDTO.class);
        typeMap.addMapping(Book::getId, BookDTO::setKey);

        TypeMap<BookDTO, Book> typeMap1 = mapper.createTypeMap(BookDTO.class, Book.class);
        typeMap1.addMapping(BookDTO::getKey, Book::setId);

    }
    public static <Origin, Destiny>  Destiny parseObject(Origin origin, Class<Destiny> destination){
        return mapper.map(origin, destination);
    }

    public static <Origin, Destiny> List<Destiny> parseListObjects(List<Origin> origin, Class<Destiny> destination){
        List<Destiny> destiniesObjects = new ArrayList<>();
        for (Origin o: origin) {
            destiniesObjects.add(mapper.map(o, destination));
        }
        return destiniesObjects;
    }
}
