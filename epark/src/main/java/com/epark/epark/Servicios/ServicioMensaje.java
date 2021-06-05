package com.epark.epark.Servicios;

import javax.servlet.http.HttpSession;

import com.epark.epark.Modelo.Contacto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioMensaje {
    @Autowired
    private RepoMensajes repoMensajes;

    private String Mensaje;

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public Iterable<Contacto> TodosMensajes() {
        try {
            this.Mensaje = "";
            return repoMensajes.findAll();
        } catch (Exception e) {
            this.Mensaje = e.getMessage();
            return null;
        }
    }

    public boolean agregar(Contacto u, HttpSession sesion) {
        try {
            repoMensajes.save(u);
            this.Mensaje = "Ultimo aviso: Datos del contacto almacenados con éxito.";
            return true;
        } catch (Exception e) {
            this.Mensaje = "Error al registrar el contacto.";
            return false;
        }
    }

    public Contacto buscar(int id) {
        try {
            Contacto buscado = repoMensajes.FindByIdContacto(id);
            return buscado == null ? new Contacto() : buscado;
        } catch (Exception e) {
            this.Mensaje = "No se encuentra el contacto";
            return null;
        }
    }

    public boolean eliminar(int id) {
        try {
            Contacto contacto = buscar(id);
            if (contacto == null || contacto.getIdContacto() == 0) {
                return false;
            }
            repoMensajes.delete(contacto);
            this.Mensaje = "Ultimo aviso: Contacto eliminado con éxito";
            return true;
        } catch (Exception e) {
            this.Mensaje = "Error al eliminar el contacto.";
            return false;
        }
    }

}
