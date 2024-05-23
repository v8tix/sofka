package com.exercise.cuenta.service;

import com.exercise.cuenta.client.ClientRestTemplate;
import com.exercise.cuenta.dto.ClientResponse;
import com.exercise.cuenta.dto.ReportResponse;
import com.exercise.cuenta.exception.ClientNotFoundException;
import com.exercise.cuenta.exception.InvalidDateRangeException;
import com.exercise.cuenta.mapper.ReportMapper;
import com.exercise.cuenta.model.Account;
import com.exercise.cuenta.model.Movement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final AccountService accountService;
    private final MovementService movementService;
    private final ClientRestTemplate clientRestTemplate;
    private final ReportMapper reportMapper;

    @Override
    public List<ReportResponse> generateReport(LocalDateTime startDate, LocalDateTime endDate, UUID clientId) {
        ClientResponse clientResponse;

        try {
            clientResponse = clientRestTemplate.getClient(clientId);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ClientNotFoundException("Cliente no encontrado para el ID: " + clientId);
        }

        List<Account> accounts = accountService.getByClientId(clientId);

        if (startDate.isAfter(endDate) || startDate.equals(endDate)) {
            throw new InvalidDateRangeException("La fecha de inicio debe ser anterior a la fecha de fin");
        }

        return accounts.stream()
                .flatMap(account -> {
                    List<Movement> movements = movementService.getByAccountAndDateRange(account, startDate, endDate);
                    return movements.stream()
                            .map(movement -> {
                                ReportResponse reportResponse = reportMapper.accountToReportResponse(account);
                                reportResponse.setCliente(clientResponse.getNombre());
                                return reportMapper.movementToReportResponse(movement, reportResponse);
                            });
                })
                .collect(Collectors.toList());
    }
}