package com.example.clinicadental.clinica.service;

import com.example.clinicadental.clinica.exception.BadRequestException;
import com.example.clinicadental.clinica.exception.ResourceNotFoundException;
import com.example.clinicadental.clinica.model.Turno;
import com.example.clinicadental.clinica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    public Turno guardar(Turno turno) throws BadRequestException {
        if(pacienteService.buscar(turno.getPaciente().getId()) == null || odontologoService.buscar(turno.getOdontologo().getId()) == null){
            throw new BadRequestException("el odontologo o paciente no es válido");

        }else{
        return turnoRepository.save(turno);
        }
    }

    public void eliminar(Integer id) throws ResourceNotFoundException {
        if(buscar(id) == null){
            throw new ResourceNotFoundException("no exites el turno con id: " + id);
        }else{
        turnoRepository.deleteById(id);
        }
    }

    public Turno buscar(Integer id) {
        Turno turno=null;
        Optional<Turno> optionalTurno= turnoRepository.findById(id);
        if (optionalTurno.isPresent()){
            turno= optionalTurno.get();
        }
        return turno;
    }

    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }

    public Turno actualizar(Turno turno) throws ResourceNotFoundException {
        if (buscar(turno.getId()) == null) {
            throw new ResourceNotFoundException("no exite el turno con id: " + turno.getId());
        } else if (pacienteService.buscar(turno.getPaciente().getId()) == null) {
            throw new ResourceNotFoundException("no exites el paciente con id: " + turno.getPaciente().getId());
        } else if (odontologoService.buscar(turno.getOdontologo().getId()) == null) {
            throw new ResourceNotFoundException("no exites el odontologo con id: " + turno.getOdontologo().getId());
        } else {
            return turnoRepository.save(turno);
        }
    }

}
