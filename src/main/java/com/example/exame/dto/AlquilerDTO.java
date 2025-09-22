package com.example.exame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlquilerDTO {

    private Long id;
    private LocalDate fechaSalida;
    private LocalDate fechaEntrada;
    private String observacion;
    private Long empresaId;  // Referencia a la empresa asociada
    private List<DetalleAlquilerDTO> detallesAlquiler;  // Detalles de alquiler

    // Getters y Setters
}


