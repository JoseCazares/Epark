package com.epark.epark.Servicios;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.epark.epark.Modelo.Automovil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioAutomovil {
    @Autowired
    private RepoAutomovil repoauto;

    private String Mensaje;

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public boolean agregar(Automovil auto) {
        try {
            repoauto.save(auto);
            this.Mensaje = "Ultimo aviso: Automovil registrado con éxito.";
            return true;
        } catch (Exception e) {
            this.Mensaje = "Ultimo aviso: Error al registrar el automovil.";
            return false;
        }
    }

    public boolean editar(Automovil auto) {
        try {
            Automovil automovil2 = buscar(auto.getIdAutomovil());
            automovil2.setMarca(auto.getMarca());
            automovil2.setModelo(auto.getModelo());
            automovil2.setPlacas(auto.getPlacas());
            automovil2.setColor(auto.getColor());
            automovil2.setDescripcion(auto.getDescripcion());
            automovil2.setIdAutomovil(auto.getIdAutomovil());
            repoauto.save(automovil2);
            this.Mensaje = "Ultimo aviso: Automovil editado con éxito.";
            return true;

        } catch (Exception e) {
            this.Mensaje = "Ultimo aviso: Error al editar el automovil.";
            return false;
        }
    }

    public boolean eliminar(int id) {
        try {
            Automovil auto = buscar(id);
            if (auto == null || auto.getIdAutomovil() == 0) {
                return false;
            }
            repoauto.delete(auto);
            this.Mensaje = "Ultimo aviso: Automovil eliminado con éxito";
            return true;
        } catch (Exception e) {
            this.Mensaje = "Ultimo aviso: Error al eliminar el automovil.";
            return false;
        }
    }

    public Iterable<Automovil> autosClientes(HttpSession sesion) {
        try {
            this.Mensaje = "";
            int id = Integer.parseInt(sesion.getAttribute("iduser").toString());
            return repoauto.carrosCliente(id);
        } catch (Exception e) {
            this.Mensaje = e.getMessage();
            return null;
        }
    }

    public Automovil buscar(int id) {
        try {
            Automovil buscado = repoauto.FindByIdAutomovil(id);
            return buscado == null ? new Automovil() : buscado;
        } catch (Exception e) {
            this.Mensaje = "No se encuentra el automovil";
            return null;
        }
    }

    public Automovil GetByMarcaAndUser(int id, String marca) {
        try {
            Automovil buscado = repoauto.GetExactId(id, marca);
            return buscado == null ? new Automovil() : buscado;
        } catch (Exception e) {
            this.Mensaje = "No se encuentra el automovil";
            return null;
        }
    }

    public boolean IsthereCar(int id) {
        try {
            ArrayList<Automovil> buscado = repoauto.carrosCliente(id);
            if (buscado.size() == 0 || buscado == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
