package com.example.Integrador.securityConfiguration;

import com.example.Integrador.service.AppUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AppUsuarioService appUsuarioService;
    // cifrador para encriptar
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bcryptPasswordEncoder);
        provider.setUserDetailsService(appUsuarioService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //desactivamos las referencias cruzadas// en un proyecto real lo tenemos que activar
                .csrf().disable()
                .authorizeRequests()
               //ACA DOY LOS PERMISOS
                //SOLO DA PERMISO A UNO SOLO
                .antMatchers("/odontologos/**","/pacientes/**").hasRole("ADMIN")
                //DA PERMISO A LOS DOS USUARIOS
                .antMatchers( "/turnos/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                //SI ESTAS LOGUEADO ACCEDES
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutUrl("/logout");
    }

}
