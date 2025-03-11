package com.example.Integrador.controller;


import com.example.Integrador.entity.Odontologo;
import com.example.Integrador.exception.BadRequestException;
import com.example.Integrador.exception.ResourceNotFoundException;
import com.example.Integrador.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;


    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologo(id);
        if(odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        }else {
            throw new ResourceNotFoundException("Error al buscar id: "+id+" no se encontró.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos() throws BadRequestException {
        List<Odontologo> odontologos= odontologoService.listarOdontologos();
        if(odontologos.isEmpty()){
            throw new BadRequestException("La lista se encuentra vacia");
        }else{
            return ResponseEntity.ok(odontologos);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologo(id);
        if(odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Se elimino correctamente el odontologo con ID: "+id);
        }else{
            throw new ResourceNotFoundException("No se encontro el id asociado: "+id);
            //return ResponseEntity.badRequest().body("No se encontró el odontologo a eliminar con ID:  "+id);
        }
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Odontologo> buscarPorMatricula(@PathVariable String matricula) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorMatricula(matricula);
        if(odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        }else {
            throw new ResourceNotFoundException("Error al buscar matricula: "+matricula+" no se encontró.");
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException{
        //vamos a consultar si ese odontologo existe
        Optional<Odontologo> odontologobuscado= odontologoService.buscarOdontologo(odontologo.getId());
        if(odontologobuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("se actualizó coorectamente al odontologo "+odontologo.getNombre()+" "+odontologo.getApellido());
        }
        else{
            throw new BadRequestException("El odontólogo con id: "+odontologo.getId()+" no se encuentra.");
        }
    }


}