package com.exercise.cliente.service;

import com.exercise.cliente.model.Client;
import com.exercise.cliente.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public Client save(Client client) {
        log.info("Saving client: {}", client);
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public void delete(UUID clienteId) {
        log.info("Deleting client with ID: {}", clienteId);
        clientRepository.deleteById(clienteId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> getAll() {
        log.info("Fetching all clients");
        return clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> getById(UUID clienteId) {
        log.info("Fetching client with ID: {}", clienteId);
        return clientRepository.findById(clienteId);
    }


}
