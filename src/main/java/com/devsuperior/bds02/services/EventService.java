package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;

public interface EventService {

    EventDTO update(Long id, EventDTO dto);
}
