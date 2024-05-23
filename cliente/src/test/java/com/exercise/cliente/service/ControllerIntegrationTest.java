package com.exercise.cliente.service;

import com.exercise.cliente.model.Client;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ComponentScan(basePackages = "com.exercise.cliente")
public class ControllerIntegrationTest extends BaseTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        final Client client = new Client();
        client.setClienteId(UUID.randomUUID());
        client.setNombre("Marco Almeida");
        client.setIdentificacion("1720565488");
        clientService.save(client);
    }

    @Test
    public void testGetClient() {
        final String url = String.format("http://localhost:%d/clientes", port);
        final ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        String responseBody = response.getBody();
        assertThat(responseBody).isNotEmpty();

        final List<String> clientIds = JsonPath.parse(responseBody).read("$.[*].clienteId");
        assertThat(clientIds).isNotEmpty();


        for (String clientId : clientIds) {
            assertThat(UUID.fromString(clientId)).isInstanceOf(UUID.class);
        }
    }
}
