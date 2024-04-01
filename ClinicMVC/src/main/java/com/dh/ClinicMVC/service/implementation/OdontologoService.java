package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.exception.ResourceNotFoundException;
import com.dh.ClinicMVC.repository.IOdontologoRepository;
import com.dh.ClinicMVC.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
        if(odontologoOptional.isPresent()) {
            return odontologoOptional.get();
        } else {
            throw new ResourceNotFoundException("No se encontró el odontologo con  id " + id);
        }
    }

    @Override
    public String actualizar(Odontologo odontologo) {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(odontologo.getId());
        if (odontologoOptional.isPresent()) {
            odontologoRepository.save(odontologo);
            return "Se actualizó el odontologo con id " + odontologo.getId();
        } else {
            throw new ResourceNotFoundException("No se encontró el odontologo con  id " + odontologo.getId());
        }
    }

    @Override
    public Odontologo findByMatricula(String matricula) {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findByMatricula(matricula);

        if (odontologoOptional.isPresent()) {
            return odontologoOptional.get();
        } else {
            throw new ResourceNotFoundException("No se encontró el odontólogo con matrícula " + matricula);
        }
    }

    @Override
    public String eliminar(Long id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if (odontologo.isPresent()){
            odontologoRepository.deleteById(id);
            return "Se elimino correctamente el odontologo!";
        } else {
            throw new ResourceNotFoundException("No se encontró el odontologo con  id " + id);
        }
    }
}
