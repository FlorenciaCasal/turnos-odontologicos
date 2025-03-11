package com.example.Integrador.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class AppUsuario implements UserDetails {
    //acale podemos decir si solo es unico ect
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String userName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private AppUsuarioRol appUsuarioRol;

    public AppUsuario(String nombre, String userName, String email, String password, AppUsuarioRol appUsuarioRol) {
        this.nombre = nombre;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.appUsuarioRol = appUsuarioRol;
    }

    public AppUsuario() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appUsuarioRol.name());
        return Collections.singletonList(grantedAuthority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
