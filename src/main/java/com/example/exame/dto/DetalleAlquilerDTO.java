package com.example.exame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetalleAlquilerDTO {

    private Long id;
    private int cantidad;
    private BigDecimal precio;
    private Long alquilerId;  // Referencia al alquiler
    private Long equipoId;    // Referencia al equipo alquilado

    // Getters y Setters
}


