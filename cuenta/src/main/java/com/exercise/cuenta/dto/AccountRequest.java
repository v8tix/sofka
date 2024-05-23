package com.exercise.cuenta.dto;

import com.exercise.cuenta.validation.Groups;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
@Getter
@Setter
public class AccountRequest {

    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    @JsonProperty("clienteId")
    private UUID clienteId;

    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    @JsonProperty("estado")
    private Boolean estado;

    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    @JsonProperty("numeroCuenta")
    private String numeroCuenta;

    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    @JsonProperty("tipoCuenta")
    private String tipoCuenta;

    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    @JsonProperty("saldoInicial")
    private BigDecimal saldoInicial;





}