package com.example.clinicadental;

import com.example.clinicadental.clinica.model.Domicilio;
import com.example.clinicadental.clinica.model.Odontologo;
import com.example.clinicadental.clinica.model.Paciente;
import com.example.clinicadental.clinica.model.Turno;
import com.example.clinicadental.clinica.service.OdontologoService;
import com.example.clinicadental.clinica.service.PacienteService;
import com.example.clinicadental.clinica.service.TurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TurnoIntegrationTest {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void listarTurnos() throws Exception {
        System.out.println("==============================");
        System.out.println("TEST BUSCAR TODOS LOS TURNOS");
        System.out.println("==============================");
        Domicilio domicilio = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente paciente = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));
        Turno turno = turnoService.guardar(new Turno(paciente,odontologo,new Date()));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/turnos"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void buscarPorId() throws Exception{
        System.out.println("==============================");
        System.out.println("TEST BUSCAR TURNO POR ID");
        System.out.println("==============================");
        Domicilio domicilio = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente paciente = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));
        Turno turno = turnoService.guardar(new Turno(paciente,odontologo,new Date()));

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.get("/turnos/{id}", turno.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assert.assertEquals("application/json", response.getResponse().getContentType());
    }

    @Test
    public void CrearTurno() throws Exception{
        System.out.println("==============================");
        System.out.println("TEST CREAR TURNO");
        System.out.println("==============================");
        Domicilio domicilio = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente paciente = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));
        Turno turno = turnoService.guardar(new Turno(paciente,odontologo,new Date()));

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String turnoJson = writer.writeValueAsString(turno);


        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(turnoJson))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());

    }

    @Test
    public void ActualizarTurno() throws Exception{
        System.out.println("==============================");
        System.out.println("TEST ACTUALIZAR TURNO");
        System.out.println("==============================");

        Domicilio domicilio = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente paciente = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));
        Odontologo odontologo2 = odontologoService.guardar(new Odontologo("002", "Martin", "Rodriguez"));
        Turno turno = turnoService.guardar(new Turno(paciente,odontologo,new Date()));
        Turno turnoAct = (new Turno(turno.getId(),paciente,odontologo2,new Date()));

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String turnoJson = writer.writeValueAsString(turnoAct);


        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.put("/turnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(turnoJson))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());

    }
}
