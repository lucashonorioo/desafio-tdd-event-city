package com.devsuperior.bds02.services.impl;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.exceptions.exception.DatabaseException;
import com.devsuperior.bds02.exceptions.exception.ResourceNotFoundException;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.CityService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CityDTO> findAll() {
        List<City> cities = cityRepository.findAll(Sort.by("name"));
        return cities.stream().map(CityDTO::new).toList();
    }

    @Transactional
    @Override
    public CityDTO insert(CityDTO cityDTO) {
        City city = new City();
        toDTO(cityDTO, city);
        city = cityRepository.save(city);
        return new CityDTO(city);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void delete(Long id) {
        if(id == null || id <= 0){
            throw new RuntimeException("O id deve ser positivo e não nulo");
        }
        if(!cityRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try{
            cityRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Recurso possui referencia com outros dados");
        }
    }

    private void toDTO(CityDTO cityDTO, City city){
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());
    }
}
