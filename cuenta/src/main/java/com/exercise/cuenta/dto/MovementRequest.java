package com.exercise.cuenta.dto;

import com.exercise.cuenta.validation.Groups;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class MovementRequest {

    @JsonProperty("cuentaId")
    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    private UUID cuentaId;

    @JsonProperty("tipoMovimiento")
    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    private String tipoMovimiento;

    @JsonProperty("valor")
    @NotNull( groups = {Groups.OnCreate.class, Groups.OnUpdate.class})
    private BigDecimal valor;

    @JsonProperty("saldo")
    @NotNull( groups = {Groups.OnUpdate.class})
    private BigDecimal saldo;

    @JsonProperty("fecha")
    @NotNull( groups = {Groups.OnUpdate.class})
    private LocalDateTime fecha;





}