package com.biblioteca.rest.bidireccional.apiPublicaciones.controllers;

import com.biblioteca.rest.bidireccional.apiPublicaciones.entity.Comentarios;
import com.biblioteca.rest.bidireccional.apiPublicaciones.entity.Publicaciones;
import com.biblioteca.rest.bidireccional.apiPublicaciones.repository.ComentariosRepository;
import com.biblioteca.rest.bidireccional.apiPublicaciones.repository.PublicacionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios")
public class ComentariosController {

    @Autowired
    private ComentariosRepository comentariosRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @GetMapping("/publicacion/{id}")
    public ResponseEntity<Page<Comentarios>> findAllComentarios(@PathVariable Integer id, Pageable pageable){
        return new ResponseEntity<>(comentariosRepository.findByPublicacionId(id,pageable),HttpStatus.OK);
    }

    @GetMapping("/{id}/publicacion/{idPost}")
    public ResponseEntity<Comentarios> findComentarioById(@PathVariable Integer id, @PathVariable Integer idPost){

        Optional<Comentarios> optionalComentarios = comentariosRepository.findByIdAndPublicacionId(id,idPost);

        return new ResponseEntity<>(comentariosRepository.save(optionalComentarios.get()),HttpStatus.OK);
    }

    @PostMapping("/publicacion/{id}")
    public ResponseEntity<Comentarios> addComentarioToPublicacion(@PathVariable Integer id, @Valid @RequestBody Comentarios comentario){

        Optional<Publicaciones> publicacionesOptional = publicacionRepository.findById(id);

        if(publicacionesOptional.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        comentario.setPublicacion(publicacionesOptional.get());
        return new ResponseEntity<>(comentariosRepository.save(comentario),HttpStatus.OK);
    }

    @PutMapping("/{id}/publicacion/{idPost}")
    public ResponseEntity<Comentarios> updateComentario(@PathVariable Integer id, @PathVariable Integer idPost, @Valid @RequestBody Comentarios comentario){

        Optional<Publicaciones> publicacionesOptional = publicacionRepository.findById(idPost);

        if(publicacionesOptional.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Comentarios> ComentariosOptional = comentariosRepository.findById(id);

        if(ComentariosOptional.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        comentario.setId(id);
        comentario.setPublicacion(publicacionesOptional.get());
        return new ResponseEntity<>(comentariosRepository.save(comentario),HttpStatus.OK);
    }

    @DeleteMapping("/{id}/publicacion/{idPost}")
    public ResponseEntity<Comentarios> deleteComentario(@PathVariable Integer id,@PathVariable Integer idPost){

        Optional<Publicaciones> publicacionesOptional = publicacionRepository.findById(idPost);

        if(publicacionesOptional.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Comentarios> ComentariosOptional = comentariosRepository.findById(id);

        if(ComentariosOptional.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        comentariosRepository.delete(ComentariosOptional.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
