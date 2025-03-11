package com.example.Integrador.controller;

import com.example.Integrador.dto.TurnoDTO;
import com.example.Integrador.entity.Odontologo;
import com.example.Integrador.entity.Paciente;
import com.example.Integrador.entity.Turno;
import com.example.Integrador.exception.BadRequestException;
import com.example.Integrador.exception.ResourceNotFoundException;
import com.example.Integrador.service.OdontologoService;
import com.example.Integrador.service.PacienteService;
import com.example.Integrador.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;


    @PostMapping
    public ResponseEntity<TurnoDTO> guardarTurno(@RequestBody Turno turno){
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologo(turno.getOdontologo().getId());
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(turno.getPaciente().getId());

        if(odontologoBuscado.isPresent()&& pacienteBuscado.isPresent()){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos() throws BadRequestException{
        List<TurnoDTO> turnosDTO = turnoService.listarTurnos();
        if(turnosDTO.isEmpty()){
            throw new BadRequestException("La lista se encuentra vacia");
        }else{
            return ResponseEntity.ok(turnosDTO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<TurnoDTO> turnoBuscado= turnoService.buscarPorID(id);
        if(turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }else{
            throw new ResourceNotFoundException("Error al buscar id: "+id+" no se encontró.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado= turnoService.buscarPorID(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se elimino correctamente el turno solicitado con ID: "+id);
        }else{
            throw new ResourceNotFoundException("No existe el id asociado :"+id);
            //return ResponseEntity.badRequest().body("No existe el turno con ID: "+id);
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) throws BadRequestException{
        Optional<TurnoDTO> turnoBuscado= turnoService.buscarPorID(turno.getId());
        if(turnoBuscado.isPresent()){
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Se actualizo correctamente el turno solicitado con ID: "+turno.getId());
        }else{
           throw new BadRequestException("No existe o no se puede actualizar el turno con ID: "+turno.getId());
        }
    }


}