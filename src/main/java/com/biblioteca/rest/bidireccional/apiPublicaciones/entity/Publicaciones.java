package com.biblioteca.rest.bidireccional.apiPublicaciones.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;


@Entity
@Table(name = "publicaciones")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Publicaciones extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Lob
    private String contenido;

    @NotNull
    @Size(max = 250)
    private String descripcion;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String titulo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Publicaciones that = (Publicaciones) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
