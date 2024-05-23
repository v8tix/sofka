package com.exercise.cliente.service;

import com.exercise.cliente.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {

    Client save(Client client);

    void delete(UUID clienteId);
    List<Client> getAll();

    Optional<Client> getById(UUID clienteId);


}