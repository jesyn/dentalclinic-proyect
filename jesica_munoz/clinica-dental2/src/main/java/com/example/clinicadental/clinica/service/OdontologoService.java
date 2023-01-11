package com.example.clinicadental.clinica.service;


import com.example.clinicadental.clinica.exception.ResourceNotFoundException;
import com.example.clinicadental.clinica.model.Odontologo;
import com.example.clinicadental.clinica.model.Paciente;
import com.example.clinicadental.clinica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;


    public Odontologo guardar(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public void eliminar(Integer id) throws ResourceNotFoundException {
        if(buscar(id) == null){
           throw new ResourceNotFoundException("no exite un odontologo con id: " + id);
        }else{
        odontologoRepository.deleteById(id);
        }
    }

    public Odontologo buscar(Integer id){
        Odontologo odontologo=null;
        Optional<Odontologo> optionalOdontologo= odontologoRepository.findById(id);
        if (optionalOdontologo.isPresent()){
            odontologo= optionalOdontologo.get();
        }
        return odontologo;
    }

    public List<Odontologo> buscarTodos(){
        return odontologoRepository.findAll();
    }

    public Odontologo actualizar(Odontologo odontologo) throws ResourceNotFoundException{
        if(buscar(odontologo.getId()) == null){
            throw new ResourceNotFoundException("no exite un odontologo con id: " + odontologo.getId());
        }else{
            return odontologoRepository.save(odontologo);
        }
    }

}
