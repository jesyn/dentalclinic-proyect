package com.example.clinicadental.serviceTest;

import com.example.clinicadental.clinica.exception.ResourceNotFoundException;
import com.example.clinicadental.clinica.model.Domicilio;
import com.example.clinicadental.clinica.model.Odontologo;
import com.example.clinicadental.clinica.model.Paciente;
import com.example.clinicadental.clinica.service.OdontologoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    public void agregarYBuscarOdontologosTest() throws ResourceNotFoundException{
        System.out.println("==============================");
        System.out.println("TEST AGREGAR Y BUSCAR ODONTOLOGO");
        System.out.println("==============================");
        Odontologo odontologo = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));

        Assert.assertNotNull(odontologoService.buscar(odontologo.getId()));
        odontologoService.eliminar(odontologo.getId());
    }

    @Test
    public void eliminarOdontologoTest() throws ResourceNotFoundException {
        System.out.println("==============================");
        System.out.println("TEST ELIMINAR ODONTOLOGO");
        System.out.println("==============================");
        Odontologo odontologo = odontologoService.guardar(new Odontologo("002", "Martin", "Rodriguez"));
        odontologoService.eliminar(odontologo.getId());
        Assert.assertTrue(odontologoService.buscar(odontologo.getId()) == null);

    }

    @Test
    public void buscarTodosOdontologoTest() throws ResourceNotFoundException{
        System.out.println("==============================");
        System.out.println("TEST BUSCAR TODOS LOS ODONTOLOGOS");
        System.out.println("==============================");
        Odontologo odontologo1 = new Odontologo("003", "Nicolas", "Avigliano");
        Odontologo odontologo2 = new Odontologo("004", "Violeta", "Matorras");

        odontologoService.guardar(odontologo1);
        odontologoService.guardar(odontologo2);

        int tamanio = odontologoService.buscarTodos().size();
        Assert.assertEquals(tamanio, odontologoService.buscarTodos().size());

        odontologoService.eliminar(odontologo1.getId());
        odontologoService.eliminar(odontologo2.getId());
    }

    @Test
    public void  actualizarOdontologoTest() throws ResourceNotFoundException{
        System.out.println("==============================");
        System.out.println("TEST ACTUALIZAR ODONTOLOGO");
        System.out.println("==============================");
        Odontologo odontologo1 = odontologoService.guardar(new Odontologo("003", "Nicolas", "Avigliano"));
        Odontologo odontologoAcutalizado = new Odontologo(odontologo1.getId(), "004", "Nicolas", "Avigliano");
        odontologoAcutalizado = odontologoService.actualizar(odontologoAcutalizado);


        Assert.assertEquals(odontologoService.buscar(odontologoAcutalizado.getId()).getMatricula(),"004");

        System.out.println(odontologoAcutalizado);
        odontologoService.eliminar(odontologoAcutalizado.getId());

    }

}
