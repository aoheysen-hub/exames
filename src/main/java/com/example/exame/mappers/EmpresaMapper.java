package com.example.exame.mappers;

import com.example.exame.dto.EmpresaDTO;
import com.example.exame.entity.Empresa;
import com.example.exame.mappers.base.BaseMappers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpresaMapper extends BaseMappers<Empresa, EmpresaDTO> {
}

