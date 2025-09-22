package com.example.exame.service.impl;

import com.example.exame.controller.exceptions.ResourceNotFoundException;
import com.example.exame.dto.AlquilerDTO;
import com.example.exame.entity.Alquiler;
import com.example.exame.mappers.AlquilerMapper;
import com.example.exame.repository.AlquilerRepository;
import com.example.exame.service.service.AlquilerService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlquilerServiceImpl implements AlquilerService {
    private final AlquilerRepository alquilerRepository;
    private final AlquilerMapper alquilerMapper;

    public AlquilerServiceImpl(AlquilerRepository alquilerRepository, AlquilerMapper alquilerMapper) {
        this.alquilerRepository = alquilerRepository;
        this.alquilerMapper = alquilerMapper;
    }

    @Override
    public AlquilerDTO create(AlquilerDTO alquilerDTO) throws ServiceException {
        try {
            Alquiler alquiler = alquilerMapper.toEntity(alquilerDTO);
            Alquiler alquilerSaved = alquilerRepository.save(alquiler);
            return alquilerMapper.toDTO(alquilerSaved);
        } catch (Exception e) {
            throw new ServiceException("Error al crear Alquiler", e);
        }
    }

    @Override
    public AlquilerDTO update(Long id, AlquilerDTO alquilerDTO) throws ServiceException {
        try {
            Alquiler alquiler = alquilerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Alquiler no encontrado"));
            alquiler.setFechaSalida(alquilerDTO.getFechaSalida());
            alquiler.setFechaEntrada(alquilerDTO.getFechaEntrada());
            alquiler.setObservacion(alquilerDTO.getObservacion());
            Alquiler alquilerUpdated = alquilerRepository.save(alquiler);
            return alquilerMapper.toDTO(alquilerUpdated);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar Alquiler", e);
        }
    }

    @Override
    public AlquilerDTO findById(Long id) throws ServiceException {
        try {
            Alquiler alquiler = alquilerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Alquiler con ID " + id + " no fue encontrado"));
            return alquilerMapper.toDTO(alquiler);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al leer el alquiler con id " + id, e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            if (!alquilerRepository.findById(id).isPresent()) {
                throw new ResourceNotFoundException("Alquiler con ID " + id + " no fue encontrado");
            }
            alquilerRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el alquiler con id " + id, e);
        }
    }

    @Override
    public List<AlquilerDTO> findAll() throws ServiceException {
        try {
            List<Alquiler> alquileres = alquilerRepository.findAll();
            return alquilerMapper.toDTOs(alquileres);
        } catch (Exception e) {
            throw new ServiceException("Error al listar los alquileres", e);
        }
    }
}
