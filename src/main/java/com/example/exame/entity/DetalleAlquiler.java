package com.example.exame.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "TBL_DETALLES_ALQUILER")
public class DetalleAlquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;
    private BigDecimal precio;

    // Relación con TBL_ALQUILER (N:1)
    @ManyToOne
    @JoinColumn(name = "alquiler_id")
    private Alquiler alquiler;

    // Relación con TBL_EQUIPOS (N:1)
    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    // Getters and setters
}


