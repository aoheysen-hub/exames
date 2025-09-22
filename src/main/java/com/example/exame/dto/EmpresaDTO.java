package com.example.exame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpresaDTO {

    private Long id;
    private String nombre;
    private String ruc;
    private String direccion;

    // Getters y Setters
}


