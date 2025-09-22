package com.example.exame.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "TBL_EQUIPOS")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private int cantidad;
    private BigDecimal precio;

    // Relación con TBL_DETALLES_ALQUILERS (1:N)
    @OneToMany(mappedBy = "equipo")
    private List<DetalleAlquiler> detallesAlquiler;

    // Getters and setters
}


