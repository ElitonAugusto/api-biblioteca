package br.com.biblioteca.services;

import br.com.biblioteca.DTO.PersonDTO;
import br.com.biblioteca.exceptions.ResourceNotFoundException;
import br.com.biblioteca.mapper.Mapper;
import br.com.biblioteca.models.Person;
import br.com.biblioteca.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll(){
        return Mapper.parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findyById(Long id){
        Person entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nada para esse ID"));
        return Mapper.parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create (PersonDTO person){
        Person entity = Mapper.parseObject(person, Person.class);
        return Mapper.parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update (PersonDTO person){

        Person entity = repository.findById(person.getId()).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nada para esse ID"));
        Person newPerson = new Person();
        newPerson.setId(person.getId());
        newPerson.setFirstName(person.getFirstName());
        newPerson.setLastName(person.getLastName());
        newPerson.setAddress(person.getAddress());
        newPerson.setGender(person.getGender());

        return Mapper.parseObject(repository.save(newPerson), PersonDTO.class);
    }

    public void delete (Long id){

        Person entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nada para esse ID"));

        repository.delete(entity);
    }
}
