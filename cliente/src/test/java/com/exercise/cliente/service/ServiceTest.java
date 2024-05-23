package com.exercise.cliente.service;

import com.exercise.cliente.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@ComponentScan(basePackages = "com.exercise.cliente")
public class ServiceTest extends BaseTest {

    @Autowired
    private ClientService clientService;

    @Test
    public void testService() {
        final Client client = new Client();
        client.setClienteId(UUID.randomUUID());
        client.setNombre("Marco Almeida");
        client.setDireccion("La Concepcion");
        clientService.save(client);

        List<Client> clients = clientService.getAll();
        assertThat(clients).hasSize(1);
    }
}

