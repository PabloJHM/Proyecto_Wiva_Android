package com.example.chrno.proyecto_final.clases;

import java.io.IOException;
import java.io.InputStream;


public class Login extends InputStream {

    String autentificado, message, errorCode;
    Usuario usuario;

    public Login(String autentificado, String message, String errorCode, Usuario usuario) {
        this.autentificado = autentificado;
        this.message = message;
        this.errorCode = errorCode;
        this.usuario = usuario;
    }

    public String getAutentificado() {
        return autentificado;
    }

    public void setAutentificado(String autentificado) {
        this.autentificado = autentificado;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
