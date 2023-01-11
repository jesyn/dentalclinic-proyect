package com.example.clinicadental;


import com.example.clinicadental.clinica.model.Odontologo;
import com.example.clinicadental.clinica.service.OdontologoService;
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


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologoIntegrationTest {

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listarOdontologo() throws Exception {
        System.out.println("==============================");
        System.out.println("TEST BUSCAR TODOS LOS ODONTOLOGOS");
        System.out.println("==============================");
        Odontologo odontologo = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/odontologos"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    public void buscarPorId() throws Exception{
        System.out.println("==============================");
        System.out.println("TEST BUSCAR ODONTOLOGO POR ID");
        System.out.println("==============================");
        Odontologo odontologo = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}", odontologo.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assert.assertEquals("application/json", response.getResponse().getContentType());
    }

    @Test
    public void CrearPaciente() throws Exception{
        System.out.println("==============================");
        System.out.println("TEST CREAR ODONTOLOGO");
        System.out.println("==============================");
        Odontologo odontologo = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String odontologoJson = writer.writeValueAsString(odontologo);


        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontologoJson))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());

    }

    @Test
    public void actualizarOdontologo() throws Exception{
        System.out.println("==============================");
        System.out.println("TEST ACTUALIZAR ODONTOLOGO");
        System.out.println("==============================");
        Odontologo odontologo = odontologoService.guardar(new Odontologo("001", "Martin", "Rodriguez"));
        Odontologo odontologoAct = (new Odontologo(odontologo.getId(),"002", "Martin", "Rodriguez"));

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String odontologoJson = writer.writeValueAsString(odontologoAct);

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.put("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontologoJson))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
