package com.example.Integrador.service;

import com.example.Integrador.entity.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    OdontologoService odontologoService;

    @Test
    @Order(1)
    void guardarOdontologo() {
        Odontologo odontologoAGuardar= new Odontologo("Lucas", "Martinez", "MN1452");
        Odontologo odontologoGuardado= odontologoService.guardarOdontologo(odontologoAGuardar);
        assertEquals(8L, odontologoAGuardar.getId());
    }

    @Test
    @Order(2)
    void buscarOdontologoPorId(){
        Long idABuscar= 8L;
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologo(idABuscar);
        assertTrue(odontologoBuscado.isPresent());
    }

    @Test
    @Order(3)
    void buscarOdontologoPorMatricula(){
        String matriculaABuscar= "MN1452";
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorMatricula(matriculaABuscar);
        assertTrue(odontologoBuscado.isPresent());
    }

    @Test
    @Order(4)
    void actualizarOdontologo() {
        Odontologo odontologoAActualizar= new Odontologo(8L, "Juan", "Caries", "MP541");
        odontologoService.actualizarOdontologo(odontologoAActualizar);
        assertEquals("Caries",odontologoService.buscarOdontologo(8L).get().getApellido());
    }

    @Test
    @Order(5)
    void buscarOdontologos() {
        List<Odontologo> listaEncontrada= odontologoService.listarOdontologos();
        assertEquals(8, listaEncontrada.size());
    }

    @Test
    @Order(6)
    void eliminarOdontologo() {
        Long idEliminar= 8L;
        odontologoService.eliminarOdontologo(idEliminar);
        Optional<Odontologo> odontologoEliminado= odontologoService.buscarOdontologo(idEliminar);
        assertFalse(odontologoEliminado.isPresent());
    }

}