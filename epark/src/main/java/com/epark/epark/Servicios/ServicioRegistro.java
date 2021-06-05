package com.epark.epark.Servicios;

import com.epark.epark.Modelo.Registro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioRegistro {

    @Autowired
    private RepoRegistro repoRegistro;

    public String Mensaje;

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public boolean agregar(Registro reg) {
        try {
            repoRegistro.save(reg);
            this.Mensaje = "Reserva agregada con éxito";
            return true;
        } catch (Exception e) {
            this.Mensaje = "Error al realizar la reserva";
            return false;
        }
    }

    public boolean Cancelar(int id) {
        try {
            Registro reg = ListarUnicoRegistro(id);
            if (reg == null || reg.getIdRegistro() == 0) {
                return false;
            }
            reg.setEstatus("C");
            repoRegistro.save(reg);
            this.Mensaje = "Registro cancelado correctamente";
            return true;

        } catch (Exception e) {
            this.Mensaje = "Error al cancelar el registro";
            return false;
        }
    }

    public boolean IsActiveRegistro(int iduser) {
        try {
            Registro reg = repoRegistro.IsActive(iduser);
            if (reg == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;

        }
    }

    public Iterable<Registro> ListarRegistros(int id) {
        try {
            this.Mensaje = "";
            return repoRegistro.ObtenerRegistros(id);
        } catch (Exception e) {
            this.Mensaje = e.getMessage();
            return null;
        }
    }

    public Registro ListarUnicoRegistro(int id) {
        try {
            this.Mensaje = "";
            return repoRegistro.ObtenerRegistroUnico(id);
        } catch (Exception e) {
            this.Mensaje = e.getMessage();
            return null;
        }
    }

}
