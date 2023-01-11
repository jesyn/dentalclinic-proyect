package com.example.clinicadental.serviceTest;

import com.example.clinicadental.clinica.exception.BadRequestException;
import com.example.clinicadental.clinica.exception.ResourceNotFoundException;
import com.example.clinicadental.clinica.model.Domicilio;
import com.example.clinicadental.clinica.model.Odontologo;
import com.example.clinicadental.clinica.model.Paciente;
import com.example.clinicadental.clinica.model.Turno;
import com.example.clinicadental.clinica.service.OdontologoService;
import com.example.clinicadental.clinica.service.PacienteService;
import com.example.clinicadental.clinica.service.TurnoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private PacienteService pacienteService;


    @Test
    public void agregarYBuscarTurnosTest() throws ResourceNotFoundException, BadRequestException {
        System.out.println("==============================");
        System.out.println("TEST AGREGAR Y BUSCAR TURNOS");
        System.out.println("==============================");
        Domicilio domicilio = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente paciente = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));
        Turno turno = turnoService.guardar(new Turno(paciente,odontologo,new Date()));

        Assert.assertNotNull(turnoService.buscar(turno.getId()));
        turnoService.eliminar(turno.getId());
    }

    @Test
    public void eliminarOdontologoTest() throws ResourceNotFoundException, BadRequestException{
        System.out.println("==============================");
        System.out.println("TEST ELIMINAR TURNO");
        System.out.println("==============================");
        Domicilio domicilio = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente paciente = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));
        Turno turno = turnoService.guardar(new Turno(paciente,odontologo,new Date()));

        turnoService.eliminar(turno.getId());
        Assert.assertTrue(turnoService.buscar(turno.getId()) == null);

    }

    @Test
    public void buscarTodosOdontologoTest() throws ResourceNotFoundException, BadRequestException{
        System.out.println("==============================");
        System.out.println("TEST BUSCAR TODOS LOS TURNOS");
        System.out.println("==============================");

        Domicilio domicilio = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente paciente = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));
        Turno turno = turnoService.guardar(new Turno(paciente,odontologo,new Date()));

        Domicilio domicilio2 = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente paciente2 = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio2));
        Odontologo odontologo2 = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));
        Turno turno2 = turnoService.guardar(new Turno(paciente2,odontologo2,new Date()));

        List<Turno> turnos = turnoService.buscarTodos();
        Assert.assertTrue(!turnos.isEmpty());
        Assert.assertTrue(turnos.size() > 0);
        System.out.println(turnos);


        turnoService.eliminar(turno.getId());
        turnoService.eliminar(turno2.getId());
    }

    @Test
    public void  actualizarTurnoTest() throws ResourceNotFoundException, BadRequestException{
        System.out.println("==============================");
        System.out.println("TEST ACTUALIZAR TURNO");
        System.out.println("==============================");

        Domicilio domicilio1 = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente paciente1 = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio1));
        Odontologo odontologo1 = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));
        Odontologo odontologo2 = odontologoService.guardar(new Odontologo("002", "Martin", "Rodriguez"));
        Turno turno1 = turnoService.guardar(new Turno(paciente1,odontologo1,new Date()));
        Turno turnoActualizado = turnoService.actualizar(new Turno(turno1.getId(),paciente1,odontologo2,new Date()));

        Assert.assertEquals(turnoService.buscar(turnoActualizado.getId()).getOdontologo().getMatricula(),"002");

        System.out.println(turnoActualizado);
        turnoService.eliminar(turnoActualizado.getId());

    }

}
