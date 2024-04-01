package com.dh.ClinicMVC;

import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.repository.IOdontologoRepository;
import com.dh.ClinicMVC.repository.IPacienteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DataInitializer {
    private final IOdontologoRepository iOdontologoRepository;
    private final IPacienteRepository iPacienteRepository;

    @Autowired
    public DataInitializer(IOdontologoRepository iOdontologoRepository, IPacienteRepository iPacienteRepository) {
        this.iOdontologoRepository = iOdontologoRepository;
        this.iPacienteRepository = iPacienteRepository;
    }

    @PostConstruct
    public void initData() {
        // Odontologos
        Odontologo odontologo1 = new Odontologo(null, "Daymara", "Barrios", "A1234", null);
        Odontologo odontologo2 = new Odontologo(null, "Yarita", "Hinojosa", "B1234", null);
        Odontologo odontologo3 = new Odontologo(null, "Carlos", "Hernandez", "C1234", null);
        Odontologo odontologo4 = new Odontologo(null, "Yumey", " la Torre", "D1234", null);

        iOdontologoRepository.save(odontologo1);
        iOdontologoRepository.save(odontologo2);
        iOdontologoRepository.save(odontologo3);
        iOdontologoRepository.save(odontologo4);

        // Pacientes
        Paciente paciente1 = new Paciente(null, "Brenda", "Hernandez", "1234", this.createLocalData("2007-10-13"), null, null);
        Paciente paciente2 = new Paciente(null, "Briana", "Hernandez", "5678", this.createLocalData("2010-01-03"), null, null);
        Paciente paciente3 = new Paciente(null, "Niurka", "Rodriguez", "9012", this.createLocalData("2017-04-12"), null, null);
        Paciente paciente4 = new Paciente(null, "Jesus", "Dias", "3456", this.createLocalData("2024-09-01"), null, null);

        iPacienteRepository.save(paciente1);
        iPacienteRepository.save(paciente2);
        iPacienteRepository.save(paciente3);
        iPacienteRepository.save(paciente4);
    }

    private LocalDate createLocalData(String fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha, formatter);
        return date;
    }
}
