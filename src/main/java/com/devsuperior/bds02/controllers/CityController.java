package com.devsuperior.bds02.controllers;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    ResponseEntity<List<CityDTO>> findAll(){
        List<CityDTO> cityDTOS = cityService.findAll();
        return ResponseEntity.ok().body(cityDTOS);
    }

    @PostMapping
    ResponseEntity<CityDTO> insert(@RequestBody CityDTO cityDTO){
        CityDTO dto = cityService.insert(cityDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<CityDTO> delete(@PathVariable Long id){
        cityService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
