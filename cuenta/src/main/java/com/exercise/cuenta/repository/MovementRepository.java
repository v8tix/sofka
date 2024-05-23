package com.exercise.cuenta.repository;

import com.exercise.cuenta.model.Account;
import com.exercise.cuenta.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface MovementRepository extends JpaRepository<Movement, UUID> {
    Optional<Movement> findTopByAccountOrderByFechaDesc(Account account);
    List<Movement> findByAccountAndFechaBetween(Account account, LocalDateTime startDate, LocalDateTime endDate);
}
