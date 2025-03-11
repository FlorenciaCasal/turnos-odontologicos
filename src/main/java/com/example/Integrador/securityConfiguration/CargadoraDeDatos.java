package com.example.Integrador.securityConfiguration;


import com.example.Integrador.entity.*;
import com.example.Integrador.repository.PacienteRepository;
import com.example.Integrador.repository.TurnoRepository;
import com.example.Integrador.repository.UserRepository;
import com.example.Integrador.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class CargadoraDeDatos implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OdontologoRepository odontologoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    TurnoRepository turnoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //ROL ADMINISTRADOR
        String passAdmin = "pass";
        // pass es la CONTRASEÑA!!!!
        String passCifradoAdmin = passwordEncoder.encode(passAdmin);
        System.out.println("La contraseña cifrada es: " + passCifradoAdmin);

        //ROL USUARIO
        String passUser = "pass";
        String passCifradoUser = passwordEncoder.encode(passUser);
        System.out.println("La contraseña cifrada es: " + passCifradoUser);

        AppUsuario usuarioAInsertarADMIN = new AppUsuario("florencia", "flor_admin", "flor_admin@digitalhouse.com", passCifradoAdmin, AppUsuarioRol.ROLE_ADMIN);
        System.out.println("La contraseña cifrada es la siguiente::::::::" + passCifradoAdmin);
        userRepository.save(usuarioAInsertarADMIN);
        AppUsuario usuarioAInsertarUSER = new AppUsuario("florencia", "flor_user", "flor_user@digitalhouse.com", passCifradoUser, AppUsuarioRol.ROLE_USER);
        System.out.println("La contraseña cifrada es la siguiente::::::::" + passCifradoUser);
        userRepository.save(usuarioAInsertarUSER);

        Odontologo odon1 = new Odontologo("Angeles", "Diez", "1254");
        Odontologo odon2 = new Odontologo("Mauro", "Gonzalez", "57663");
        Odontologo odon3 = new Odontologo("Juan Cruz", "Cerrato", "8574");
        Odontologo odon4 = new Odontologo("Mario", "Juarez", "7552");
        Odontologo odon5 = new Odontologo("Mariano", "Costa", "668");
        Odontologo odon6 = new Odontologo("Gabriela", "Zuchard", "81745");
        Odontologo odon7 = new Odontologo("malena", "recarey", "2555");

        odontologoRepository.save(odon1);
        odontologoRepository.save(odon2);
        odontologoRepository.save(odon3);
        odontologoRepository.save(odon4);
        odontologoRepository.save(odon5);
        odontologoRepository.save(odon6);
        odontologoRepository.save(odon7);

        Paciente paci1 = new Paciente("Lola", "Cáceres", "1547852", LocalDate.of(2000, 12, 3), new Domicilio("San Martin", "145", "Esquel", "Chubut"), "lola@gmail.com");
        Paciente paci2 = new Paciente("Matias", "Puig", "2347852", LocalDate.of(2015, 2, 28), new Domicilio("Antartida", "2483", "Monte Grande", "Buenos Aires"), "mati@gmail.com");
        Paciente paci3 = new Paciente("Paulina", "ávila", "3347859", LocalDate.of(2021, 6, 12), new Domicilio("Alvear", "448", "El bolsón", "rio negro"), "paulina@gmail.com");
        Paciente paci4 = new Paciente("Brenda", "Uriarte", "2847852", LocalDate.of(2020, 3, 1), new Domicilio("sarmiento", "1354", "bariloche", "rio negro"), "brenda@gmail.com");
        Paciente paci5 = new Paciente("Juan", "Nogueira", "14847852", LocalDate.of(2018, 9, 26), new Domicilio("belgrano", "778", "Luis guillon", "buenos aires"), "juan@gmail.com");
        Paciente paci6 = new Paciente("pedro", "sanchez", "8547845", LocalDate.of(2017, 5, 17), new Domicilio("bajada 0", "345", "las grutas", "rio negro"), "pedro@gmail.com");
        Paciente paci7 = new Paciente("valentina", "mazzuco", "5147845", LocalDate.of(2023, 5, 9), new Domicilio("laprida", "612", "neuquen", "neuquen"), "valen@gmail.com");

        pacienteRepository.save(paci1);
        pacienteRepository.save(paci2);
        pacienteRepository.save(paci3);
        pacienteRepository.save(paci4);
        pacienteRepository.save(paci5);
        pacienteRepository.save(paci6);
        pacienteRepository.save(paci7);

        Turno turno1 = new Turno(paci1, odon5, LocalDate.of(2023, 7, 12), LocalTime.of(8, 30));
        Turno turno2 = new Turno(paci2, odon4, LocalDate.of(2023, 8, 3), LocalTime.of(16, 45));
        Turno turno3 = new Turno(paci6, odon1, LocalDate.of(2023, 7, 25), LocalTime.of(9, 10));
        Turno turno4 = new Turno(paci3, odon2, LocalDate.of(2023, 8, 1), LocalTime.of(17, 30));
        Turno turno5 = new Turno(paci5, odon6, LocalDate.of(2023, 7, 14), LocalTime.of(9, 0));
        Turno turno6 = new Turno(paci1, odon4, LocalDate.of(2023, 7, 13), LocalTime.of(14, 45));
        Turno turno7 = new Turno(paci6, odon3, LocalDate.of(2023, 7, 28), LocalTime.of(18, 30));

        turnoRepository.save(turno1);
        turnoRepository.save(turno2);
        turnoRepository.save(turno3);
        turnoRepository.save(turno4);
        turnoRepository.save(turno5);
        turnoRepository.save(turno6);
        turnoRepository.save(turno7);

    }
}

