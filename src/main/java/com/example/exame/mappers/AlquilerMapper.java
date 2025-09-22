package com.example.exame.mappers;

import com.example.exame.dto.AlquilerDTO;
import com.example.exame.entity.Alquiler;
import com.example.exame.mappers.base.BaseMappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DetalleAlquilerMapper.class, EmpresaMapper.class})
public interface AlquilerMapper extends BaseMappers<Alquiler, AlquilerDTO> {

    @Mapping(source = "empresa.id", target = "empresaId")
    AlquilerDTO toDTO(Alquiler alquiler);
}

