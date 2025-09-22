package com.example.exame.service.impl;

import com.example.exame.controller.exceptions.ResourceNotFoundException;
import com.example.exame.dto.EquipoDTO;
import com.example.exame.entity.Equipo;
import com.example.exame.mappers.EquipoMapper;
import com.example.exame.repository.EquipoRepository;
import com.example.exame.service.service.EquipoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {
    private final EquipoRepository equipoRepository;
    private final EquipoMapper equipoMapper;

    public EquipoServiceImpl(EquipoRepository equipoRepository, EquipoMapper equipoMapper) {
        this.equipoRepository = equipoRepository;
        this.equipoMapper = equipoMapper;
    }

    @Override
    public EquipoDTO create(EquipoDTO equipoDTO) throws ServiceException {
        try {
            Equipo equipo = equipoMapper.toEntity(equipoDTO);
            Equipo equipoSaved = equipoRepository.save(equipo);
            return equipoMapper.toDTO(equipoSaved);
        } catch (Exception e) {
            throw new ServiceException("Error al crear Equipo", e);
        }
    }

    @Override
    public EquipoDTO update(Long id, EquipoDTO equipoDTO) throws ServiceException {
        try {
            Equipo equipo = equipoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));
            equipo.setDescripcion(equipoDTO.getDescripcion());
            equipo.setCantidad(equipoDTO.getCantidad());
            equipo.setPrecio(equipoDTO.getPrecio());
            Equipo equipoUpdated = equipoRepository.save(equipo);
            return equipoMapper.toDTO(equipoUpdated);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar Equipo", e);
        }
    }

    @Override
    public EquipoDTO findById(Long id) throws ServiceException {
        try {
            Equipo equipo = equipoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipo con ID " + id + " no fue encontrado"));
            return equipoMapper.toDTO(equipo);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al leer el equipo con id " + id, e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            if (!equipoRepository.findById(id).isPresent()) {
                throw new ResourceNotFoundException("Equipo con ID " + id + " no fue encontrado");
            }
            equipoRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el equipo con id " + id, e);
        }
    }

    @Override
    public List<EquipoDTO> findAll() throws ServiceException {
        try {
            List<Equipo> equipos = equipoRepository.findAll();
            return equipoMapper.toDTOs(equipos);
        } catch (Exception e) {
            throw new ServiceException("Error al listar los equipos", e);
        }
    }
}

