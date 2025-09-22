package com.example.exame.service.impl;

import com.example.exame.controller.exceptions.ResourceNotFoundException;
import com.example.exame.dto.EmpresaDTO;
import com.example.exame.entity.Empresa;
import com.example.exame.mappers.EmpresaMapper;
import com.example.exame.repository.EmpresaRepository;
import com.example.exame.service.service.EmpresaService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper) {
        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;
    }

    @Override
    public EmpresaDTO create(EmpresaDTO empresaDTO) throws ServiceException {
        try {
            Empresa empresa = empresaMapper.toEntity(empresaDTO);
            Empresa empresaSaved = empresaRepository.save(empresa);
            return empresaMapper.toDTO(empresaSaved);
        } catch (Exception e) {
            throw new ServiceException("Error al crear Empresa", e);
        }
    }

    @Override
    public EmpresaDTO update(Long id, EmpresaDTO empresaDTO) throws ServiceException {
        try {
            Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada"));
            empresa.setNombre(empresaDTO.getNombre());
            empresa.setRuc(empresaDTO.getRuc());
            empresa.setDireccion(empresaDTO.getDireccion());
            Empresa empresaUpdated = empresaRepository.save(empresa);
            return empresaMapper.toDTO(empresaUpdated);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar Empresa", e);
        }
    }

    @Override
    public EmpresaDTO findById(Long id) throws ServiceException {
        try {
            Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Empresa con ID " + id + " no fue encontrada"));
            return empresaMapper.toDTO(empresa);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al leer la empresa con id " + id, e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            if (!empresaRepository.findById(id).isPresent()) {
                throw new ResourceNotFoundException("Empresa con ID " + id + " no fue encontrada");
            }
            empresaRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar la empresa con id " + id, e);
        }
    }

    @Override
    public List<EmpresaDTO> findAll() throws ServiceException {
        try {
            List<Empresa> empresas = empresaRepository.findAll();
            return empresaMapper.toDTOs(empresas);
        } catch (Exception e) {
            throw new ServiceException("Error al listar las empresas", e);
        }
    }
}
