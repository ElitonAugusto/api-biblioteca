package br.com.biblioteca.services;

import br.com.biblioteca.exceptions.ResourceNotFoundException;
import br.com.biblioteca.DTO.PersonDTO;
import br.com.biblioteca.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll(){

        return repository.findAll();
    }

    public PersonDTO findyById(Long id){
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nada para esse ID"));
    }

    public PersonDTO create (PersonDTO personDTO){

        return repository.save(personDTO);
    }

    public PersonDTO update (PersonDTO personDTO){

        PersonDTO entity = repository.findById(personDTO.getId()).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nada para esse ID"));

        entity.setId(personDTO.getId());
        entity.setFirstName(personDTO.getFirstName());
        entity.setLastName(personDTO.getLastName());
        entity.setAdress(personDTO.getAdress());
        entity.setGender(personDTO.getGender());

        return repository.save(entity);
    }

    public void delete (Long id){

        PersonDTO entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nada para esse ID"));

        repository.delete(entity);
    }
}
