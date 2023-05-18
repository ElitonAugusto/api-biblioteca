package br.com.biblioteca.controllers;

import br.com.biblioteca.DTO.PersonDTO;
import br.com.biblioteca.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonService service;

    @GetMapping
    public List<PersonDTO> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public PersonDTO findById(@PathVariable Long id){
        return service.findyById(id);
    }

    @PostMapping
    public PersonDTO create (@RequestBody PersonDTO personDTO){
        return service.create(personDTO);
    }

    @PutMapping
    public PersonDTO update (@RequestBody PersonDTO personDTO){
        return service.update(personDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
