package br.com.biblioteca.services;

import br.com.biblioteca.DTO.BookDTO;
import br.com.biblioteca.controllers.BookController;
import br.com.biblioteca.exceptions.RequiredObjectIsNullException;
import br.com.biblioteca.exceptions.ResourceNotFoundException;
import br.com.biblioteca.mapper.BookMapper;
import br.com.biblioteca.models.Book;
import br.com.biblioteca.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public List<BookDTO> findAll(){
        List<BookDTO> bookDTOS = BookMapper.parseListObjects(repository.findAll(), BookDTO.class);
        bookDTOS.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
        return bookDTOS;
    }

    public BookDTO findyById(Long id){
        Book entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nada para esse ID"));
        BookDTO bookDTO = BookMapper.parseObject(entity, BookDTO.class);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookDTO;
    }

    public BookDTO create (BookDTO book){
        if(book == null) throw new RequiredObjectIsNullException();
        Book entity = BookMapper.parseObject(book, Book.class);
        BookDTO bookDTO = BookMapper.parseObject(repository.save(entity),BookDTO.class);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());
        return bookDTO;
    }

    public BookDTO update (BookDTO book){
        if(book == null) throw new RequiredObjectIsNullException();
        Book entity = repository.findById(book.getKey()).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nada para esse ID"));
        Book newBook = new Book();
        newBook.setId(book.getKey());
        newBook.setAuthor((book.getAuthor()));
        newBook.setLaunchDate(book.getLaunchDate());
        newBook.setPrice(book.getPrice());
        newBook.setTitle(book.getTitle());

        BookDTO bookDTO = BookMapper.parseObject(repository.save(newBook), BookDTO.class);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());
        return bookDTO;
    }

    public void delete (Long id){

        Book entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nada para esse ID"));

        repository.delete(entity);
    }
}
