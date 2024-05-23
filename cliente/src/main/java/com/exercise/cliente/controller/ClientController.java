package com.exercise.cliente.controller;

import com.exercise.cliente.dto.ClientRequest;
import com.exercise.cliente.exception.ClientNotFoundException;
import com.exercise.cliente.mapper.ClientMapper;
import com.exercise.cliente.model.Client;
import com.exercise.cliente.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientRequest clientRequest) {
        Client client = clientMapper.convertToCliente(clientRequest);
        Client savedClient = clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        List<Client> clients = clientService.getAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable UUID id) {
        Client client = clientService.getById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with ID: " + id));
        return ResponseEntity.ok(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> completeUpdate(
            @PathVariable UUID id,
            @RequestBody ClientRequest clientRequest) {
        return updateClient(id, clientRequest);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Client> update(
            @PathVariable UUID id,
            @RequestBody ClientRequest clientRequest) {
        return updateClient(id, clientRequest);
    }

    private ResponseEntity<Client> updateClient(UUID id, ClientRequest clientRequest) {
        Client client = clientService.getById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with ID: " + id));
        clientMapper.updateClienteFromRequest(clientRequest, client);
        Client updatedClient = clientService.save(client);
        return ResponseEntity.ok(updatedClient);
    }
}