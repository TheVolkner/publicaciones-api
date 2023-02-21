package com.biblioteca.rest.bidireccional.apiPublicaciones.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Entity
@Table(name = "comentarios")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Comentarios extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Lob
    private String texto;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="publicacion_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Publicaciones publicacion = new Publicaciones();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comentarios that = (Comentarios) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
