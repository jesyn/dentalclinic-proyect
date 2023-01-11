package com.example.clinicadental.clinica.service;

import com.example.clinicadental.clinica.exception.ResourceNotFoundException;
import com.example.clinicadental.clinica.repository.DomicilioRepository;
import com.example.clinicadental.clinica.model.Domicilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {

    @Autowired
    private DomicilioRepository domicilioRepository;


    public Domicilio guardar(Domicilio d){
        domicilioRepository.save(d);
        return d;
    }

    public void eliminar(Integer id) throws ResourceNotFoundException {
        if(buscar(id) == null){
            throw new ResourceNotFoundException("no existe paciente con id: " + id);
        }else{
            domicilioRepository.deleteById(id);
        }
    }

    public Domicilio buscar(Integer id){
        Domicilio domicilio=null;
        Optional<Domicilio> optionaDomicilio= domicilioRepository.findById(id);
        if (optionaDomicilio.isPresent()){
            domicilio= optionaDomicilio.get();
        }
        return domicilio;
    }

    public List<Domicilio> buscarTodos(){
        return domicilioRepository.findAll();
    }

    public Domicilio actualizar(Domicilio domicilio) throws ResourceNotFoundException{
        if(buscar(domicilio.getId()) == null){
            throw new ResourceNotFoundException("no existe domicilio con id: " + domicilio.getId());
        }else{
            return domicilioRepository.save(domicilio);
        }
    }

}
