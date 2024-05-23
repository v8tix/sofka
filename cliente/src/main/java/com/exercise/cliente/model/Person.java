package com.exercise.cliente.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public class Person extends Base {
    @Column(name = "nombre")
    protected String nombre;

    @Column(name = "identificacion", unique = true)
    protected String identificacion;

    @Column(name = "genero")
    protected String genero;

    @Column(name = "edad")
    protected int edad;

    @Column(name = "telefono")
    protected String telefono;

    @Column(name = "direccion")
    protected String direccion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(identificacion, person.identificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacion);
    }
}

