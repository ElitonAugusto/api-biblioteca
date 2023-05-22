package br.com.biblioteca.mapper;

import br.com.biblioteca.DTO.PersonDTO;
import br.com.biblioteca.models.Person;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.List;

public class PersonMapper {

    private static ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        configureIdPersonAndPersonDTO();
    }

    private static void configureIdPersonAndPersonDTO() {
        TypeMap<Person, PersonDTO> typeMap = mapper.createTypeMap(Person.class, PersonDTO.class);
        typeMap.addMapping(Person::getId, PersonDTO::setKey);

        TypeMap<PersonDTO, Person> typeMap1 = mapper.createTypeMap(PersonDTO.class, Person.class);
        typeMap1.addMapping(PersonDTO::getKey, Person::setId);

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
