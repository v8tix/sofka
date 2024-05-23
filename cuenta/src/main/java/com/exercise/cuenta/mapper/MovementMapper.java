package com.exercise.cuenta.mapper;

import com.exercise.cuenta.dto.MovementRequest;
import com.exercise.cuenta.exception.MappingException;
import com.exercise.cuenta.model.Movement;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MovementMapper {

    private final ModelMapper modelMapper;

    @PostConstruct
    private void configureModelMapper() {
        modelMapper.addMappings(new PropertyMap<MovementRequest, Movement>() {
            @Override
            protected void configure() {
                skip(destination.getMovimientoId());
            }
        });
    }

    public Movement convertToMovimiento(MovementRequest movementRequest) {
        try {
            return modelMapper.map(movementRequest, Movement.class);
        } catch (Exception e) {
            throw new MappingException("Error converting MovementRequest to Movement", e);
        }
    }

    public void updateMovimientoFromRequest(MovementRequest movementRequest, Movement movement) {
        try {
            modelMapper.map(movementRequest, movement);
        } catch (Exception e) {
            throw new MappingException("Error updating Movement from MovementRequest", e);
        }
    }
}