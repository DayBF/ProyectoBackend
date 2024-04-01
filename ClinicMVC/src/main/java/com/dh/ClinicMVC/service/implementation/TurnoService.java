package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.dto.request.TurnoRequestDTO;
import com.dh.ClinicMVC.dto.response.TurnoResponseDTO;
import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.entity.Turno;
import com.dh.ClinicMVC.exception.ResourceNotFoundException;
import com.dh.ClinicMVC.repository.ITurnoRepository;
import com.dh.ClinicMVC.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @Autowired
    public TurnoService(ITurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public TurnoResponseDTO guardar(TurnoRequestDTO turnoRequestDTO) {

        Odontologo odontologo = odontologoService.buscarPorId(turnoRequestDTO.getOdontologo_id());
        Paciente paciente = pacienteService.buscarPorId(turnoRequestDTO.getPaciente_id());

        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        //convertir el String del turnoRequestDTO a LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //creamos el LocalDate que vamos a tener que persistir en la BD
        LocalDate date = LocalDate.parse(turnoRequestDTO.getFecha(), formatter);
        turno.setFecha(date);

        turnoRepository.save(turno);

        TurnoResponseDTO turnoResponseDTO = this.mapperDeTuroATurnoResponseDTO(turno);

        return turnoResponseDTO;


        // ------ ESTO LO REVISAS LUEGO ------

//        //mapear el dto que recibimos a una entidad
//        //instanciar turnoEntity -> para persitirlo en la BD
//        Turno turnoEntity = new Turno(); //Turno(null, null, null, null)
//
//        //instanciamos Paciente
//        Paciente paciente = new Paciente();
//        paciente.setId(turnoRequestDTO.getPaciente_id());
//
//        //instanciamos Odontologo
//        Odontologo odontologo = new Odontologo();
//        odontologo.setId(turnoRequestDTO.getOdontologo_id());
//
//        //seteamos Paciente y Odontologo a la entidad Turno
//        turnoEntity.setOdontologo(odontologo);
//        turnoEntity.setPaciente(paciente);
//
//        //convertir el String del turnoRequestDTO a LocalDate
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        //creamos el LocalDate que vamos a tener que persistir en la BD
//        LocalDate date = LocalDate.parse(turnoRequestDTO.getFecha(), formatter);
//
//        //seteamos al turno la fecha
//        turnoEntity.setFecha(date);
//
//        //persistir el turno en la BD
//        turnoRepository.save(turnoEntity);
//        //acá ya tenemos la entidad con id
//
//        //ESTE ES EL CAMINO DE VUELTA HACIA EL CONTROLADOR
//        //PORQUE YA SE PERSISTIÓ LA ENTIDAD
//        //mapear la entidad persistida en un dto
//        TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO(); //TurnoDto(null, null, null, null)
//        turnoResponseDTO.setId(turnoEntity.getId());
//        turnoResponseDTO.setOdontologo_id(turnoEntity.getOdontologo().getId());
//        turnoResponseDTO.setPaciente_id(turnoEntity.getPaciente().getId());
//        turnoResponseDTO.setFecha(turnoEntity.getFecha().toString());
//
//        return turnoResponseDTO;
    }

    @Override
    public List<TurnoResponseDTO> listarTodos() {
        List<Turno> listaDeTurnos = turnoRepository.findAll();
        List<TurnoResponseDTO> listDeTurnosADevolverDTO = new ArrayList<>();

        for (int i = 0; i < listaDeTurnos.size(); i++) {
            Turno turnoActaul = listaDeTurnos.get(i);
            TurnoResponseDTO turnoActualDTO = mapperDeTuroATurnoResponseDTO(turnoActaul);
            listDeTurnosADevolverDTO.add(turnoActualDTO);
        }

        return listDeTurnosADevolverDTO;
    }

    @Override
    public TurnoResponseDTO buscarPorId(Long id) {
        //buscar por id
        Optional<Turno> turnoABuscar = turnoRepository.findById(id);

        if (turnoABuscar.isPresent()) {
            //mapear el turno al dto a devolver
            TurnoResponseDTO turnoResponseDTO = this.mapperDeTuroATurnoResponseDTO(turnoABuscar.get());
            return turnoResponseDTO;
        } else {
            //agregan un log que diga que "No se encontró el turno con  id " + id
            throw new ResourceNotFoundException("No se encontró el turno con  id " + id);
        }
    }


    @Override
    public void actualizarFecha(Turno turno) {

    }

    // Con esta funcion que estamos reutilizando en varios lugares convertimos de la entidad Turno al TurnoResponseDTO
    // porque el dto es el que necesitamos devolver.
    private TurnoResponseDTO mapperDeTuroATurnoResponseDTO(Turno turno){
        TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();
        turnoResponseDTO.setId(turno.getId());
        turnoResponseDTO.setOdontologo_id(turno.getOdontologo().getId());
        turnoResponseDTO.setPaciente_id(turno.getPaciente().getId());
        turnoResponseDTO.setFecha(turno.getFecha().toString());
        return turnoResponseDTO;
    }

}
