package com.example.clinicadental.clinica.controller;

import com.example.clinicadental.clinica.exception.ResourceNotFoundException;
import com.example.clinicadental.clinica.model.Paciente;
import com.example.clinicadental.clinica.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")

public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Operation(summary = "buscar un paciente por id")
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Integer id){
        Paciente paciente= pacienteService.buscar(id);
        if(paciente != null){
            return ResponseEntity.ok(paciente);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "borrar un paciente por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        pacienteService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("eliminado");
    }

    @Operation(summary = "listar todos los pacientes")
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @Operation(summary = "crear un nuevo paciente")
    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente){

        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @Operation(summary = "actualizar un paciente")
    @PutMapping
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente) throws ResourceNotFoundException{
        return ResponseEntity.ok(pacienteService.actualizar(paciente));
    }
}
