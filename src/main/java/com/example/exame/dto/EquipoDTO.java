package com.example.exame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipoDTO {

    private Long id;
    private String descripcion;
    private int cantidad;
    private BigDecimal precio;

    // Getters y Setters
}


