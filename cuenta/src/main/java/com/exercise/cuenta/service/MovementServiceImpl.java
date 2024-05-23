package com.exercise.cuenta.service;

import com.exercise.cuenta.dto.MovementRequest;
import com.exercise.cuenta.exception.AccountNotFoundException;
import com.exercise.cuenta.exception.SaldoNoDisponibleException;
import com.exercise.cuenta.mapper.MovementMapper;
import com.exercise.cuenta.model.Account;
import com.exercise.cuenta.model.Movement;
import com.exercise.cuenta.repository.AccountRepository;
import com.exercise.cuenta.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovementServiceImpl implements MovementService {
    private final MovementRepository movementRepository;
    private final MovementMapper movementMapper;
    private final AccountRepository accountRepository;

    @Override
    public Optional<Movement> create(MovementRequest movementRequest) {
        UUID cuentaId = movementRequest.getCuentaId();
        BigDecimal movimientoValor = movementRequest.getValor();

        return accountRepository.findById(cuentaId).map(cuenta -> {
            Movement ultimoMovement = movementRepository.findTopByAccountOrderByFechaDesc(cuenta).orElse(null);
            BigDecimal saldoActual = (ultimoMovement != null) ? ultimoMovement.getSaldo() : cuenta.getSaldoInicial();
            BigDecimal nuevoSaldo = saldoActual.add(movimientoValor);

            if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
                throw new SaldoNoDisponibleException("Saldo no disponible.");
            }

            Movement movement = movementMapper.convertToMovimiento(movementRequest);
            movement.setAccount(cuenta);
            movement.setSaldo(nuevoSaldo);
            Movement savedMovement = movementRepository.save(movement);

            return Optional.of(savedMovement);
        }).orElseThrow(() -> new AccountNotFoundException(cuentaId));
    }

    @Override
    public Optional<Movement> update(UUID movimientoId, MovementRequest movementRequest) {
        return movementRepository.findById(movimientoId).map(existingMovimiento -> {
            movementMapper.updateMovimientoFromRequest(movementRequest, existingMovimiento);
            Movement updatedMovement = movementRepository.save(existingMovimiento);
            return Optional.of(updatedMovement);
        }).orElse(Optional.empty());
    }

    @Override
    public void delete(UUID id) {
        if (movementRepository.existsById(id)) {
            movementRepository.deleteById(id);
        } else {
            log.warn("Movimiento con id {} no encontrado.", id);
        }
    }

    @Override
    public List<Movement> getByAccountAndDateRange(Account account, LocalDateTime startDate, LocalDateTime endDate) {
        return movementRepository.findByAccountAndFechaBetween(account, startDate, endDate);
    }

    @Override
    public List<Movement> getAll() {
        return movementRepository.findAll();
    }

    @Override
    public Optional<Movement> getById(UUID id) {
        return movementRepository.findById(id);
    }

}