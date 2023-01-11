package com.example.clinicadental.clinica.service;


import com.example.clinicadental.clinica.exception.ResourceNotFoundException;
import com.example.clinicadental.clinica.model.Paciente;
import com.example.clinicadental.clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;


    public Paciente guardar(Paciente p) {
        p.setFechaIngreso(new Date());
        return pacienteRepository.save(p);
    }

    public void eliminar(Integer id) throws ResourceNotFoundException {
        if(buscar(id) == null){
            throw new ResourceNotFoundException("no existe paciente con id: " + id);
        }else{
        pacienteRepository.deleteById(id);
        }
    }

    public Paciente buscar(Integer id) {
        Paciente paciente=null;
        Optional<Paciente> optionaPaciente= pacienteRepository.findById(id);
            if (optionaPaciente.isPresent()){
                paciente= optionaPaciente.get();
            }
            return paciente;
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente actualizar(Paciente p) throws ResourceNotFoundException {
        if(buscar(p.getId()) == null){
            throw new ResourceNotFoundException("no existe paciente con id: " + p.getId());
        }else{
            return pacienteRepository.save(p);
        }

    }
}
