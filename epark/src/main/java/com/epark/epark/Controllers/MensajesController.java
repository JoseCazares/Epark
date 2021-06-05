package com.epark.epark.Controllers;

import javax.servlet.http.HttpSession;

import com.epark.epark.Modelo.Cliente;
import com.epark.epark.Modelo.Contacto;
import com.epark.epark.Servicios.ClienteServicios;
import com.epark.epark.Servicios.ServicioMensaje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MensajesController {
    @Autowired
    ServicioMensaje servicioMensajes;

    @Autowired
    ClienteServicios servicioCliente;

    private Cliente currentUser;

    @GetMapping("/Contacto/Listar")
    public String Listar(Model modelo, HttpSession sesion) {
        try {
            Iterable<Contacto> mensajes = servicioMensajes.TodosMensajes();
            if (mensajes.iterator().hasNext()) {
                modelo.addAttribute("contactos", mensajes);
                modelo.addAttribute("error", sesion.getAttribute("mensajeContact"));
            } else {
                modelo.addAttribute("error", "No hay contactos registrados");
            }
            int iduser = Integer.parseInt(sesion.getAttribute("iduser").toString());
            currentUser = servicioCliente.FindById(iduser);
            if (currentUser != null) {
                modelo.addAttribute("user", true);
                modelo.addAttribute("nombreU", currentUser.getUsuario());
            } else {
                modelo.addAttribute("user", false);
            }
        } catch (Exception e) {
            modelo.addAttribute("user", false);
        }
        modelo.addAttribute("contacto", new Contacto());
        return "Contacto/Listar";
    }

    @GetMapping("/Contacto/Agregar")
    public String agregar(Model modelo, HttpSession session) {
        try {
            currentUser = servicioCliente.FindById(Integer.parseInt(session.getAttribute("iduser").toString()));

            if (currentUser != null) {
                modelo.addAttribute("user", true);
                modelo.addAttribute("nombreU", currentUser.getUsuario());
            }

        } catch (Exception e) {
            modelo.addAttribute("user", false);
        }
        modelo.addAttribute("usuario", new Contacto());
        return "Contacto/Agregar";
    }

    @PostMapping("/Contacto/Agregar")
    public String agregar(Model modelo, @ModelAttribute Contacto mensajes, HttpSession session) {
        boolean res = servicioMensajes.agregar(mensajes, session);
        if (res) {
            session.setAttribute("mensajeContact", servicioMensajes.getMensaje());
            return "redirect:/Contacto/Listar";
        }
        session.setAttribute("mensaje", servicioMensajes.getMensaje());
        return "Contacto/Agregar";
    }

    @GetMapping("/Contacto/Eliminar/{id}")
    public String eliminar(@PathVariable("id") int idmensaje, Model modelo, HttpSession session) {
        try {
            currentUser = servicioCliente.FindById(Integer.parseInt(session.getAttribute("iduser").toString()));
            if (currentUser != null) {
                modelo.addAttribute("user", true);
                modelo.addAttribute("nombreU", currentUser.getUsuario());
            }
        } catch (Exception e) {
            modelo.addAttribute("user", false);
        }
        servicioMensajes.eliminar(idmensaje);
        session.setAttribute("mensajeContact", servicioMensajes.getMensaje());
        return "redirect:/Contacto/Listar";
    }
}
