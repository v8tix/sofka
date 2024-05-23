package com.exercise.cliente.mapper;

import com.exercise.cliente.dto.ClientRequest;
import com.exercise.cliente.model.Client;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    private final ModelMapper modelMapper;

    public ClientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        modelMapper.addMappings(new PropertyMap<ClientRequest, Client>() {
            @Override
            protected void configure() {
                skip(destination.getClienteId());
            }
        });
    }

    public Client convertToCliente(ClientRequest clientRequest) {
        return modelMapper.map(clientRequest, Client.class);
    }

    public void updateClienteFromRequest(ClientRequest clientRequest, Client client) {
        modelMapper.map(clientRequest, client);
    }
}
