package com.biblioteca.rest.bidireccional.apiPublicaciones.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){

    }

    public ResourceNotFoundException(String mensaje){

     super(mensaje);
    }

    public ResourceNotFoundException(String mensaje,Throwable lanzar){

     super(mensaje,lanzar);
    }
}
