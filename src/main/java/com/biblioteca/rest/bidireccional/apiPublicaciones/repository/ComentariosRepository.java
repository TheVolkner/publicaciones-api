package com.biblioteca.rest.bidireccional.apiPublicaciones.repository;


import com.biblioteca.rest.bidireccional.apiPublicaciones.entity.Comentarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ComentariosRepository extends JpaRepository<Comentarios,Integer> {

    public Page<Comentarios> findByPublicacionId(Integer PublicacionId, Pageable pageable);

    //SE PUEDE HACER PETICIONES HACIENDO USO DE LA ENTIDAD VINCULADA A LA ACTUAL
    //SOLO SE INDICA EL NOMBRE DE DICHA ENTIDAD + SU ATRIBUTO, EJEMPLO PUBLICACIONID.
    public Optional<Comentarios> findByIdAndPublicacionId(Integer Id,Integer PublicacionId);
}
