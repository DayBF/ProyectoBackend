package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.entity.Paciente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacienteServiceTest {

    @Test
    void guardar() {

        Integer pacienteId = 2;
        Paciente paciente = new Paciente();
        paciente.setNombre("Daymara");
        paciente.setApellido("Barrios");
        paciente.setDni("1234");
        //paciente.setMatricula("1234");
        PacienteService.

            assertNotNull(paciente);
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