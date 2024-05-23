package com.exercise.cliente.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.exercise.cliente.model.Client;
import com.exercise.cliente.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;
    private UUID clientId;

    @BeforeEach
    void setUp() {
        clientId = UUID.randomUUID();
        client = new Client();
        client.setClienteId(clientId);
        client.setNombre("John Doe");
    }

    @Test
    void testSave() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client savedClient = clientService.save(client);

        assertNotNull(savedClient, "The saved client should not be null");
        assertEquals(client.getClienteId(), savedClient.getClienteId(), "Client ID should match");
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testDelete() {
        doNothing().when(clientRepository).deleteById(clientId);

        clientService.delete(clientId);

        verify(clientRepository, times(1)).deleteById(clientId);
    }

    @Test
    void testGetAll() {
        List<Client> clients = Arrays.asList(client);
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.getAll();

        assertFalse(result.isEmpty(), "The client list should not be empty");
        assertEquals(1, result.size(), "The size of the client list should be 1");
        assertEquals(client.getClienteId(), result.get(0).getClienteId(), "Client ID should match");
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testGetById() {
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.getById(clientId);

        assertTrue(result.isPresent(), "The client should be present");
        assertEquals(client.getClienteId(), result.get().getClienteId(), "Client ID should match");
        verify(clientRepository, times(1)).findById(clientId);
    }
}
