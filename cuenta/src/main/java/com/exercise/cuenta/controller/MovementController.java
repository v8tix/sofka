package com.exercise.cuenta.controller;

import com.exercise.cuenta.dto.MovementRequest;
import com.exercise.cuenta.model.Movement;
import com.exercise.cuenta.validation.Groups;
import com.exercise.cuenta.service.MovementService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/movimientos")
public class MovementController {

    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }


    @PostMapping
    public ResponseEntity<Movement> create(@RequestBody @Validated(Groups.OnCreate.class) MovementRequest movementRequest) {
        Optional<Movement> movement = movementService.create(movementRequest);
        return movement.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        movementService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public List<Movement> getAll() {
        return movementService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movement> getById(@PathVariable UUID id) {
        Optional<Movement> movement = movementService.getById(id);
        return movement.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }



    @PutMapping("/{id}")
    public ResponseEntity<Movement> completeUpdate(
            @PathVariable UUID id,
            @RequestBody @Validated(Groups.OnUpdate.class) MovementRequest movementRequest
    ) {
        return updateMovement(id, movementRequest);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Movement> update(
            @PathVariable UUID id,
            @RequestBody MovementRequest movementRequest
    ) {
        return updateMovement(id, movementRequest);
    }

    private ResponseEntity<Movement> updateMovement(UUID id, MovementRequest movementRequest) {
        Optional<Movement> movement = movementService.update(id, movementRequest);
        return movement.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}