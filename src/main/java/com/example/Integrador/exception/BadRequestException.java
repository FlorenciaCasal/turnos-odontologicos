package com.example.Integrador.exception;

public class BadRequestException  extends Exception{
    public BadRequestException(String mensaje){
        super(mensaje);
    }
}
