package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.entity.Odontologo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    void guardar() {

        Integer odontologoId = 1;
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Daymara");
        odontologo.setApellido("Barrios");
        odontologo.setMatricula("1234");
        odontologoService.guardar(odontologo);

        assertNotNull(odontologo);
    }
    @Test
    public void buscarId() {

        Long odontologoId = 1L;
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Carlos");
        odontologo.setApellido("Hernandez");
        odontologo.setMatricula("C1234");
        odontologoService.buscarPorId(odontologoId);

        assertNotNull(odontologo);
    }


}