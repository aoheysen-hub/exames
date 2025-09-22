package com.example.exame.mappers;

import com.example.exame.dto.EquipoDTO;
import com.example.exame.entity.Equipo;
import com.example.exame.mappers.base.BaseMappers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipoMapper extends BaseMappers<Equipo, EquipoDTO> {
}

