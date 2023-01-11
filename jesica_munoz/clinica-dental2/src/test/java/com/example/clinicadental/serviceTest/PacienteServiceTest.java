package com.example.clinicadental.serviceTest;

import com.example.clinicadental.clinica.exception.ResourceNotFoundException;
import com.example.clinicadental.clinica.model.Domicilio;
import com.example.clinicadental.clinica.model.Paciente;
import com.example.clinicadental.clinica.service.DomicilioService;
import com.example.clinicadental.clinica.service.PacienteService;
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
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private DomicilioService domicilioService;


    @Test
    public void agregarYBuscarPacienteTest() throws ResourceNotFoundException {
        System.out.println("==============================");
        System.out.println("TEST AGREGAR Y BUSCAR PACIENTE");
        System.out.println("==============================");
        Domicilio domicilio = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente p = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));

        Assert.assertNotNull(pacienteService.buscar(p.getId()));
        pacienteService.eliminar(p.getId());

    }


    @Test
    public void eliminarPacienteTest() throws ResourceNotFoundException{
        System.out.println("==============================");
        System.out.println("TEST ELIMINAR PACIENTE");
        System.out.println("==============================");
        Domicilio domicilio = new Domicilio("forest", "613", "CABA", "Buenos Aires");
        Paciente p = pacienteService.guardar(new Paciente("Laura", "Ramirez", "12345678", new Date(), domicilio));

        pacienteService.eliminar(p.getId());
        Assert.assertTrue(pacienteService.buscar(p.getId()) == null);

    }

    @Test
    public void traerTodos() throws ResourceNotFoundException{
        System.out.println("==============================");
        System.out.println("TEST BUSCAR TODOS LOS PACIENTES");
        System.out.println("==============================");
        Domicilio domicilio = new Domicilio("Av Santa fe", "444", "CABA", "Buenos Aires");
        Paciente p = pacienteService.guardar(new Paciente("Santiago", "Paz", "88888888", new Date(), domicilio));

        List<Paciente> pacientes = pacienteService.buscarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() > 0);
        System.out.println(pacientes);

        pacienteService.eliminar(p.getId());

    }

    @Test
    public void  actualizarPacienteTest() throws ResourceNotFoundException{
        System.out.println("==============================");
        System.out.println("TEST ACTUALIZAR PACIENTE");
        System.out.println("==============================");
        Domicilio domicilio = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente p = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));
        Paciente p2 = pacienteService.actualizar((new Paciente(p.getId(), "Tomy","Pereyra", "12345678", new Date(), domicilio)));

        Assert.assertEquals(pacienteService.buscar(p2.getId()).getNombre(),"Tomy");

        System.out.println(p2);
        pacienteService.eliminar(p.getId());

    }
}
