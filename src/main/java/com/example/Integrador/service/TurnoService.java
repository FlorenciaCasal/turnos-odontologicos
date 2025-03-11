package com.example.Integrador.service;


import com.example.Integrador.dto.TurnoDTO;
import com.example.Integrador.entity.Odontologo;
import com.example.Integrador.entity.Paciente;
import com.example.Integrador.entity.Turno;
import com.example.Integrador.repository.OdontologoRepository;
import com.example.Integrador.repository.PacienteRepository;
import com.example.Integrador.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private OdontologoRepository odontologoRepository;

    public List<TurnoDTO> listarTurnos() {
        List<Turno> turnosEncontrados = turnoRepository.findAll();
        //necesito recorrer esta lista para ir pasando de un lugar a otro
        List<TurnoDTO> turnoDTOLista = new ArrayList<>();
        for (Turno turno : turnosEncontrados) {
            turnoDTOLista.add(turnoAturnoDTO(turno));
        }
        return turnoDTOLista;
    }

    public Optional<TurnoDTO> buscarPorID(Long id) {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()) {
            //el turno existe
            return Optional.of(turnoAturnoDTO(turnoBuscado.get()));
        } else {
            //es nulo
            return Optional.empty();
        }
    }

    public TurnoDTO guardarTurno(Turno turno) {
       Turno turnoguardado= turnoRepository.save(turno);
        return turnoAturnoDTO(turnoguardado);
    }

    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
    }

    public void actualizarTurno(Turno turno) {
        turnoRepository.save(turno);
    }

    private TurnoDTO turnoAturnoDTO(Turno turno) {
        //necesitamos convertirlo
        TurnoDTO turnoDTO = new TurnoDTO();
        Optional<Odontologo> odontologo = odontologoRepository.findById(turno.getOdontologo().getId());
        Optional<Paciente> paciente = pacienteRepository.findById(turno.getPaciente().getId());

        //necesitamos ahora cargarle la informacion
        turnoDTO.setId(turno.getId());
        if (paciente.isPresent() && odontologo.isPresent()) {
            turnoDTO.setPacienteId(paciente.get().getId());
            turnoDTO.setOdontologoId(odontologo.get().getId());
        }
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setHora(turno.getHora());
        //devolverlo
        return turnoDTO;
    }
    private Turno turnoDTOaTurno(TurnoDTO turnoDTO) {
        //convertir
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();
        //ahora necesitamos cargar la info
        turno.setId(turnoDTO.getId());
        paciente.setId(turnoDTO.getPacienteId());
        odontologo.setId(turnoDTO.getOdontologoId());
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(turnoDTO.getFecha());
        turno.setHora(turnoDTO.getHora());
        //necesito devolverlos
        return turno;
    }
}
