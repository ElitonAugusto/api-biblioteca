package br.com.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Exception1 extends RuntimeException{
    private static final long serialVersionUID = 1L;
}
