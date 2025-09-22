package com.example.exame.controller.general;

import com.example.exame.dto.EmpresaDTO;
import com.example.exame.service.service.EmpresaService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> findAll() throws ServiceException {
        return ResponseEntity.ok(empresaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> read(@PathVariable Long id) throws ServiceException {
        EmpresaDTO empresaDTO = empresaService.findById(id);
        return ResponseEntity.ok(empresaDTO);
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> create(@RequestBody EmpresaDTO empresaDTO) throws ServiceException {
        EmpresaDTO empresaDTO1 = empresaService.create(empresaDTO);
        return new ResponseEntity<>(empresaDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> update(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO) throws ServiceException {
        EmpresaDTO empresaDTO1 = empresaService.update(id, empresaDTO);
        return ResponseEntity.ok(empresaDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        empresaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

