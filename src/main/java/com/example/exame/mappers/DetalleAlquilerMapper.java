package com.example.exame.mappers;

import com.example.exame.dto.DetalleAlquilerDTO;
import com.example.exame.entity.DetalleAlquiler;
import com.example.exame.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleAlquilerMapper extends BaseMappers<DetalleAlquiler, DetalleAlquilerDTO> {

    @Mapping(source = "alquiler.id", target = "alquilerId")
    @Mapping(source = "equipo.id", target = "equipoId")
    DetalleAlquilerDTO toDTO(DetalleAlquiler detalleAlquiler);

    @InheritInverseConfiguration
    DetalleAlquiler toEntity(DetalleAlquilerDTO detalleAlquilerDTO);
}

