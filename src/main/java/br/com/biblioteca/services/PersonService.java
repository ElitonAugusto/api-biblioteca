package br.com.biblioteca.services;

import br.com.biblioteca.DTO.PersonDTO;
import br.com.biblioteca.controllers.PersonController;
import br.com.biblioteca.exceptions.RequiredObjectIsNullException;
import br.com.biblioteca.exceptions.ResourceNotFoundException;
import br.com.biblioteca.mapper.PersonMapper;
import br.com.biblioteca.models.Person;
import br.com.biblioteca.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll(){
        List<PersonDTO> personsDTOS = PersonMapper.parseListObjects(repository.findAll(), PersonDTO.class);
        personsDTOS.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return personsDTOS;
    }

    public PersonDTO findyById(Long id){
        Person entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nada para esse ID"));
        PersonDTO personDTO = PersonMapper.parseObject(entity, PersonDTO.class);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personDTO;
    }

    public PersonDTO create (PersonDTO person){
        if(person == null) throw new RequiredObjectIsNullException();
        Person entity = PersonMapper.parseObject(person, Person.class);
        PersonDTO personDTO = PersonMapper.parseObject(repository.save(entity), PersonDTO.class);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());
        return personDTO;
    }

    public PersonDTO update (PersonDTO person){
        if(person == null) throw new RequiredObjectIsNullException();
        Person entity = repository.findById(person.getKey()).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nada para esse ID"));
        Person newPerson = new Person();
        newPerson.setId(person.getKey());
        newPerson.setFirstName(person.getFirstName());
        newPerson.setLastName(person.getLastName());
        newPerson.setAddress(person.getAddress());
        newPerson.setGender(person.getGender());

        PersonDTO personDTO = PersonMapper.parseObject(repository.save(newPerson), PersonDTO.class);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());
        return personDTO;
    }

    public void delete (Long id){

        Person entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nada para esse ID"));

        repository.delete(entity);
    }
}
