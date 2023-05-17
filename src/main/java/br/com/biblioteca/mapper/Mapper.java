package br.com.biblioteca.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private static ModelMapper mapper = new ModelMapper();

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
