package com.exercise.cuenta.service;

import com.exercise.cuenta.dto.MovementRequest;
import com.exercise.cuenta.model.Account;
import com.exercise.cuenta.model.Movement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface MovementService {
    Optional<Movement> create(MovementRequest movementRequest);
    Optional<Movement> update(UUID movimientoId, MovementRequest movementRequest);
    void delete(UUID id);
    List<Movement> getByAccountAndDateRange(Account account, LocalDateTime startDate, LocalDateTime endDate);

    List<Movement> getAll();
    Optional<Movement> getById(UUID id);
}