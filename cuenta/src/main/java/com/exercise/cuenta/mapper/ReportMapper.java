package com.exercise.cuenta.mapper;

import com.exercise.cuenta.dto.ReportResponse;
import com.exercise.cuenta.model.Account;
import com.exercise.cuenta.model.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(target = "movimiento", source = "valor")
    @Mapping(target = "saldoDisponible", source = "saldo")
    ReportResponse movementToReportResponse(Movement movement, @MappingTarget ReportResponse reportResponse);

    @Mapping(target = "tipo", source = "tipoCuenta")
    ReportResponse accountToReportResponse(Account account);

}
