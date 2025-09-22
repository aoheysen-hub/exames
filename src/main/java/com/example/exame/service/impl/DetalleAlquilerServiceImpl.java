package com.example.exame.service.impl;

import com.example.exame.controller.exceptions.ResourceNotFoundException;
import com.example.exame.dto.DetalleAlquilerDTO;
import com.example.exame.entity.DetalleAlquiler;
import com.example.exame.mappers.DetalleAlquilerMapper;
import com.example.exame.repository.DetalleAlquilerRepository;
import com.example.exame.service.service.DetalleAlquilerService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleAlquilerServiceImpl implements DetalleAlquilerService {
    private final DetalleAlquilerRepository detalleAlquilerRepository;
    private final DetalleAlquilerMapper detalleAlquilerMapper;

    public DetalleAlquilerServiceImpl(DetalleAlquilerRepository detalleAlquilerRepository, DetalleAlquilerMapper detalleAlquilerMapper) {
        this.detalleAlquilerRepository = detalleAlquilerRepository;
        this.detalleAlquilerMapper = detalleAlquilerMapper;
    }

    @Override
    public DetalleAlquilerDTO create(DetalleAlquilerDTO detalleAlquilerDTO) throws ServiceException {
        try {
            DetalleAlquiler detalleAlquiler = detalleAlquilerMapper.toEntity(detalleAlquilerDTO);
            DetalleAlquiler detalleAlquilerSaved = detalleAlquilerRepository.save(detalleAlquiler);
            return detalleAlquilerMapper.toDTO(detalleAlquilerSaved);
        } catch (Exception e) {
            throw new ServiceException("Error al crear DetalleAlquiler", e);
        }
    }

    @Override
    public DetalleAlquilerDTO update(Long id, DetalleAlquilerDTO detalleAlquilerDTO) throws ServiceException {
        try {
            DetalleAlquiler detalleAlquiler = detalleAlquilerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DetalleAlquiler no encontrado"));
            detalleAlquiler.setCantidad(detalleAlquilerDTO.getCantidad());
            detalleAlquiler.setPrecio(detalleAlquilerDTO.getPrecio());
            DetalleAlquiler detalleAlquilerUpdated = detalleAlquilerRepository.save(detalleAlquiler);
            return detalleAlquilerMapper.toDTO(detalleAlquilerUpdated);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar DetalleAlquiler", e);
        }
    }

    @Override
    public DetalleAlquilerDTO findById(Long id) throws ServiceException {
        try {
            DetalleAlquiler detalleAlquiler = detalleAlquilerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DetalleAlquiler con ID " + id + " no fue encontrado"));
            return detalleAlquilerMapper.toDTO(detalleAlquiler);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al leer el DetalleAlquiler con id " + id, e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            if (!detalleAlquilerRepository.findById(id).isPresent()) {
                throw new ResourceNotFoundException("DetalleAlquiler con ID " + id + " no fue encontrado");
            }
            detalleAlquilerRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el DetalleAlquiler con id " + id, e);
        }
    }

    @Override
    public List<DetalleAlquilerDTO> findAll() throws ServiceException {
        try {
            List<DetalleAlquiler> detallesAlquiler = detalleAlquilerRepository.findAll();
            return detalleAlquilerMapper.toDTOs(detallesAlquiler);
        } catch (Exception e) {
            throw new ServiceException("Error al listar los DetalleAlquiler", e);
        }
    }
}
