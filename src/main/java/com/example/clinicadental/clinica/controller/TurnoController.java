package com.example.clinicadental.clinica.controller;

import com.example.clinicadental.clinica.exception.BadRequestException;

import com.example.clinicadental.clinica.exception.ResourceNotFoundException;
import com.example.clinicadental.clinica.model.Turno;
import com.example.clinicadental.clinica.service.TurnoService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private static Logger logger = Logger.getLogger(TurnoController.class);

    @Autowired
    private TurnoService turnoService;

    @Operation(summary = "buscar turno por id")
    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscar(@PathVariable Integer id){
        Turno turno = turnoService.buscar(id);
        if(turno !=null){
            return ResponseEntity.ok(turno);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "borrar turno por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        turnoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Eliminado");

    }

    @Operation(summary = "actualizar un turno")
    @PutMapping
    public ResponseEntity<Turno> actualizar(@RequestBody Turno turno) throws ResourceNotFoundException{
            return  ResponseEntity.ok(turnoService.actualizar(turno));

    }

    @Operation(summary = "listar todos los turnos")
    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @Operation(summary = "crear un turno")
    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) throws BadRequestException{
        return ResponseEntity.ok(turnoService.guardar(turno));

    }

    @ExceptionHandler({BadRequestException.class})
        public ResponseEntity<String> tratarErrorBadRequest (BadRequestException ex){
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
}
