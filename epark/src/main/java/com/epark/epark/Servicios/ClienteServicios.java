package com.epark.epark.Servicios;

import java.security.MessageDigest;

import javax.servlet.http.HttpSession;

import com.epark.epark.Modelo.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicios {
    @Autowired
    private RepoCliente repoCliente;

    private String Mensaje;

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    private byte[] encriptar(String clave) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(clave.getBytes("utf-8"));
            return digest.digest();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validar(String correo, String clave, HttpSession sesion) {
        Cliente usr = repoCliente.validar(correo, encriptar(clave));
        if (usr != null) {
            sesion.setAttribute("iduser", usr.getIdCliente());
            Mensaje = usr.getUsuario();
            return true;
        }
        Mensaje = "Datos incorrectos, intente nuevamente";
        return false;
    }

    public boolean agregar(Cliente u, String clave, HttpSession sesion) {
        try {
            u.setClave(encriptar(clave));
            repoCliente.save(u);
            sesion.setAttribute("iduser", u.getIdCliente());
            this.Mensaje = "Datos del usuario almacenados con éxito. Ahora por favor inicie sesión.";
            return true;
        } catch (Exception e) {
            this.Mensaje = "Error al registrar el usuario.Intente nuevamente";
            return false;
        }
    }

    public Cliente buscar(String correo) {
        try {
            Cliente actual = repoCliente.findbycorreo(correo);
            return actual == null ? new Cliente() : actual;
        } catch (Exception e) {
            this.Mensaje = "No se encuentra el usuario";
            return null;
        }
    }

    public Cliente FindById(int idcliente) {
        try {
            Cliente actual = repoCliente.FindByIdcliente(idcliente);
            return actual == null ? new Cliente() : actual;
        } catch (Exception e) {
            this.Mensaje = "No se encuentra el usuario";
            return null;
        }
    }

}
