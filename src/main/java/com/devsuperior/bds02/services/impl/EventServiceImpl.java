package com.devsuperior.bds02.services.impl;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.exceptions.exception.ResourceNotFoundException;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.EventService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    @Override
    public EventDTO update(Long id, EventDTO dto) {
        if(id == null || id <= 0){
            throw new RuntimeException("O id deve ser positivo e não nulo");
        }
        try {
            Event event = eventRepository.getReferenceById(id);
            toDto(dto, event);
            event = eventRepository.save(event);
            return new EventDTO(event);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    private void toDto(EventDTO dto, Event event){
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setUrl(dto.getUrl());
        event.setCity(new City(dto.getCityId(), null));
    }

}
