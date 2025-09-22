package com.example.exame.controller.general;

import com.example.exame.dto.AlquilerDTO;
import com.example.exame.service.service.AlquilerService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alquileres")
public class AlquilerController {
    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping
    public ResponseEntity<List<AlquilerDTO>> findAll() {
        return ResponseEntity.ok(alquilerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlquilerDTO> read(@PathVariable Long id) throws ServiceException {
        AlquilerDTO alquilerDTO = alquilerService.findById(id);
        return ResponseEntity.ok(alquilerDTO);
    }

    @PostMapping
    public ResponseEntity<AlquilerDTO> create(@RequestBody AlquilerDTO alquilerDTO) throws ServiceException {
        AlquilerDTO alquilerDTO1 = alquilerService.create(alquilerDTO);
        return new ResponseEntity<>(alquilerDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlquilerDTO> update(@PathVariable Long id, @RequestBody AlquilerDTO alquilerDTO) throws ServiceException {
        AlquilerDTO alquilerDTO1 = alquilerService.update(id, alquilerDTO);
        return ResponseEntity.ok(alquilerDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        alquilerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

