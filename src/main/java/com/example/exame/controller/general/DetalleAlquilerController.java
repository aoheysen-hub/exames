package com.example.exame.controller.general;

import com.example.exame.dto.DetalleAlquilerDTO;
import com.example.exame.service.service.DetalleAlquilerService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles-alquiler")
public class DetalleAlquilerController {
    private final DetalleAlquilerService detalleAlquilerService;

    public DetalleAlquilerController(DetalleAlquilerService detalleAlquilerService) {
        this.detalleAlquilerService = detalleAlquilerService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleAlquilerDTO>> findAll() {
        return ResponseEntity.ok(detalleAlquilerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleAlquilerDTO> read(@PathVariable Long id) throws ServiceException {
        DetalleAlquilerDTO detalleAlquilerDTO = detalleAlquilerService.findById(id);
        return ResponseEntity.ok(detalleAlquilerDTO);
    }

    @PostMapping
    public ResponseEntity<DetalleAlquilerDTO> create(@RequestBody DetalleAlquilerDTO detalleAlquilerDTO) throws ServiceException {
        DetalleAlquilerDTO detalleAlquilerDTO1 = detalleAlquilerService.create(detalleAlquilerDTO);
        return new ResponseEntity<>(detalleAlquilerDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleAlquilerDTO> update(@PathVariable Long id, @RequestBody DetalleAlquilerDTO detalleAlquilerDTO) throws ServiceException {
        DetalleAlquilerDTO detalleAlquilerDTO1 = detalleAlquilerService.update(id, detalleAlquilerDTO);
        return ResponseEntity.ok(detalleAlquilerDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        detalleAlquilerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

