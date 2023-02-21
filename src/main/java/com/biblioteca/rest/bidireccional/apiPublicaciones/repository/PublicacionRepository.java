package com.biblioteca.rest.bidireccional.apiPublicaciones.repository;


import com.biblioteca.rest.bidireccional.apiPublicaciones.entity.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PublicacionRepository extends JpaRepository<Publicaciones,Integer> {


}
