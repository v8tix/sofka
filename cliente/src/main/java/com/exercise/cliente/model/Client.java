package com.exercise.cliente.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "clientes", schema = "cliente")
public class Client extends Person {

    @Id
    @GeneratedValue(generator = "clienteId")
    @GenericGenerator(name = "clienteId", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "clienteId",  nullable = false, updatable = false)
    private UUID clienteId;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "contrasena")
    private String contrasena;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(this.clienteId, client.clienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clienteId);
    }
}
