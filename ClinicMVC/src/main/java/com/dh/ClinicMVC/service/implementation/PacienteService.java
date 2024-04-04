package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.exception.ResourceNotFoundException;
import com.dh.ClinicMVC.repository.IPacienteRepository;
import com.dh.ClinicMVC.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private IPacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardar(Paciente paciente) {

        return pacienteRepository.save(paciente);
    }

    @Override
    public List<Paciente> listarTodos() {

        return pacienteRepository.findAll();
    }

    @Override
    public Paciente buscarPorId(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);

        if (pacienteOptional.isPresent()) {
            return pacienteOptional.get();
        }
        throw new ResourceNotFoundException("No se encontró el paciente con  id " + id);
    }

    @Override
    public String eliminar(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            pacienteRepository.deleteById(id);
            return "Se elimino correctamente el paciente con id " + id;
        }
        throw new ResourceNotFoundException("No se encontró el paciente con  id " + id);
    }

    @Override
    public String actualizar(Paciente paciente) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(paciente.getId());
        if (pacienteOptional.isPresent()) {
            pacienteRepository.save(paciente);
            return "Se actualizo correctamente el paciente con id " + paciente.getId();
        }
        throw new ResourceNotFoundException("No se encontró el paciente con  id " + paciente.getId());
    }
}
