package com.exercise.cliente.dto;

import com.exercise.cliente.validation.Groups;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClientRequest {

    @JsonProperty("nombre")
    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    private String nombre;

    @JsonProperty("genero")
    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    private String genero;

    @JsonProperty("edad")
    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    private int edad;

    @JsonProperty("identificacion")
    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    private String identificacion;

    @JsonProperty("direccion")
    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    private String direccion;

    @JsonProperty("telefono")
    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    private String telefono;

    @JsonProperty("contrasena")
    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    private String contrasena;

    @JsonProperty("estado")
    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    private Boolean estado;
}
