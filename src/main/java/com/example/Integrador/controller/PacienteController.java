package com.example.Integrador.controller;


import com.example.Integrador.entity.Paciente;
import com.example.Integrador.exception.BadRequestException;
import com.example.Integrador.exception.ResourceNotFoundException;
import com.example.Integrador.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {
   @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id)throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(id);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else {
            //return ResponseEntity.notFound().build();
            throw new ResourceNotFoundException("Error al buscar id: "+id+" no se encontró.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes()throws BadRequestException {
        List<Paciente> pacientes = pacienteService.devolverPacientes();
        if(pacientes.isEmpty()){
            throw new BadRequestException("La lista se encuentra vacia");
        }else{
            return ResponseEntity.ok(pacientes);
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente)throws BadRequestException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizo correctamente el paciente con id: "+paciente.getId()+" Nombre: "+paciente.getNombre());
        }else{
            throw new BadRequestException("El paciente con id: "+paciente.getId()+" no se encuentra.");
            //return ResponseEntity.badRequest().body("El paciente con id: "+paciente.getId()+" no se encuentra.");
        }
    }

    @GetMapping("/email/{correo}")
    public ResponseEntity<Paciente> buscarPacientePorCorreo(@PathVariable String correo)throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorCorreo(correo);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else {
            throw new ResourceNotFoundException("El correo ingresado no se encuentra.");
            //return ResponseEntity.notFound().build(); //NOTFOUND = NO LO ENCONTRO
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok().body("Se elimino el paciente con id: "+id);
        }else{
            //no existe para eliminarlo
            //aca debemos arrojar la exception
            throw new ResourceNotFoundException("No existe el id asociado en la bdd: "+id);
        }
    }

}
