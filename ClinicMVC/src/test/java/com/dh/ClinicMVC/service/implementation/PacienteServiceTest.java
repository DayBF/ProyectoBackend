package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.repository.IPacienteRepository;
import com.dh.ClinicMVC.service.IPacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PacienteServiceTest {

   @Autowired
//    private IPacienteRepository pacienteRepository;

    @Autowired
    private IPacienteService pacienteService;


    @Test
    public void testGuardar() {
        Paciente paciente = new Paciente();
        paciente.setApellido("apellidoPaciente");
        paciente.setNombre("nombrePaciente");
        paciente.setDni("123");
        String fecha = "2024-03-05";
        LocalDate formatter = LocalDate.parse(fecha);
        Domicilio domicilioPaciente = new Domicilio();
        domicilioPaciente.setCalle("callePaciente");
        domicilioPaciente.setLocalidad("Localidad");
        domicilioPaciente.setProvincia("provincia");
        domicilioPaciente.setNumero(123);
        paciente.setDomicilio(domicilioPaciente);
        paciente.setFechaIngreso(formatter);
        Paciente guardarPaciente = pacienteService.guardar(paciente);
        assertNotNull(guardarPaciente);
        assertNotNull(guardarPaciente.getId());
        assertEquals(paciente, guardarPaciente);
        Paciente fromDb = pacienteService.buscarPorId(guardarPaciente.getId());
        assertNotNull(fromDb);
        assertEquals(guardarPaciente.getId(), fromDb.getId());

        }


    @Test
    void listarTodos() {
    }

    @Test
    void buscarPorId() {
    }

    @Test
    void eliminar() {
    }

    @Test
    void actualizar() {
    }
}