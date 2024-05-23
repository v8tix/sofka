package com.exercise.cuenta.client;

import com.exercise.cuenta.dto.ClientResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Component
public class ClientRestTemplate {

    private final RestTemplate restTemplate;
    private final String clientServiceUrl;

    public ClientRestTemplate(RestTemplate restTemplate,
                              @Value("${ms.cliente.url}") String clientServiceUrl) {
        this.restTemplate = restTemplate;
        this.clientServiceUrl = clientServiceUrl;
    }

    public ClientResponse getClient(UUID id) {
        String url = UriComponentsBuilder.fromHttpUrl(clientServiceUrl)
                .path("/clientes/{id}")
                .buildAndExpand(id)
                .toUriString();

        return restTemplate.getForObject(url, ClientResponse.class);
    }
}