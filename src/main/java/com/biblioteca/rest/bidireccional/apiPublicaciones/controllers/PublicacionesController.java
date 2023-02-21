package com.biblioteca.rest.bidireccional.apiPublicaciones.controllers;

import com.biblioteca.rest.bidireccional.apiPublicaciones.entity.Publicaciones;
import com.biblioteca.rest.bidireccional.apiPublicaciones.exceptions.ResourceNotFoundException;
import com.biblioteca.rest.bidireccional.apiPublicaciones.repository.PublicacionRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionesController {


    @Autowired
    private PublicacionRepository publicacionRepository;

    @GetMapping
    public ResponseEntity<Page<Publicaciones>> listPublicaciones(Pageable pageable){

        return new ResponseEntity<>(publicacionRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicaciones> getPublicacionById(@PathVariable Integer id,Pageable pageable){

        Optional<Publicaciones> publicacionesOptional = publicacionRepository.findById(id);

        if(publicacionesOptional.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(publicacionesOptional.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Publicaciones> addPublicacion(@Valid @RequestBody Publicaciones publicacion){

        return new ResponseEntity<>(publicacionRepository.save(publicacion),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publicaciones> updatePublicacion(@PathVariable Integer id, @Valid @RequestBody Publicaciones publicaciones){

        Optional<Publicaciones> publicacionesOptional = publicacionRepository.findById(id);

        if(publicacionesOptional.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        publicaciones.setId(id);
        return new ResponseEntity<>(publicacionRepository.save(publicaciones),HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Publicaciones> deletePublicacion(@PathVariable Integer id){

        Optional<Publicaciones> publicacionesOptional = publicacionRepository.findById(id);

        if(publicacionesOptional.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        publicacionRepository.delete(publicacionesOptional.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
