package com.example.Integrador.service;

import com.example.Integrador.entity.Domicilio;
import com.example.Integrador.entity.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    PacienteService pacienteService;


    @Test
    @Order(1)
    void guardarPaciente() {
        Paciente pacienteAGuardar= new Paciente("Lucas", "Martinez", "34178", LocalDate.of(2023,10,30), new Domicilio("san martin", "123", "bariloche", "rio negro"), "lm@gmail.com");
        Paciente pacienteGuardado= pacienteService.guardarPaciente(pacienteAGuardar);
        assertEquals(8L, pacienteAGuardar.getId());
    }


    @Test
    @Order(2)
    void buscarPacientePorId(){
        Long idABuscar= 8L;
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(idABuscar);
        assertTrue(pacienteBuscado.isPresent());
    }

    @Test
    @Order(3)
    void buscarOdontologoPorCorreo(){
        String correoABuscar= "lm@gmail.com";
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorCorreo(correoABuscar);
        assertTrue(pacienteBuscado.isPresent());
    }

    @Test
    @Order(4)
    void actualizarPacientePorId() {
        Paciente pacienteAActualizar= new Paciente(8L,"Mariano", "Martinez", "34178", LocalDate.of(2023,10,30), new Domicilio("san martin", "123", "bariloche", "rio negro"), "lm@gmail.com");
        pacienteService.actualizarPaciente(pacienteAActualizar);
        assertEquals("Mariano",pacienteService.buscarPacientePorID(8L).get().getNombre());
    }

    @Test
    @Order(5)
    void buscarPacientes() {
        List<Paciente> listaEncontrada= pacienteService.devolverPacientes();
        assertEquals(8, listaEncontrada.size());
    }

    @Test
    @Order(6)
    void eliminarPaciente() {
        Long idEliminar= 8L;
        pacienteService.eliminarPaciente(idEliminar);
        Optional<Paciente> pacienteEliminado= pacienteService.buscarPacientePorID(idEliminar);
        assertFalse(pacienteEliminado.isPresent());
    }

}