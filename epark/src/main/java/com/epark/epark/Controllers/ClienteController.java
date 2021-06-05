package com.epark.epark.Controllers;

import javax.servlet.http.HttpSession;

import com.epark.epark.Servicios.ClienteServicios;
import com.epark.epark.Modelo.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClienteController {
    @Autowired
    ClienteServicios servicioCliente;

    private Cliente currentUser;

    @GetMapping("/usuario/validar")
    public String validar(Model modelo, HttpSession session) {
        try {
            currentUser = servicioCliente.FindById(Integer.parseInt(session.getAttribute("iduser").toString()));

            if (currentUser != null) {
                modelo.addAttribute("user", true);
                modelo.addAttribute("nombreU", currentUser.getUsuario());
                modelo.addAttribute("mensajeCLI", session.getAttribute("mensajeCLI"));
            }
        } catch (Exception e) {
            modelo.addAttribute("user", false);
        }

        return "usuario/validar";
    }

    @PostMapping("/usuario/validar")
    public String agregar(@RequestParam("username") String correo, @RequestParam("password") String clave,
            Model modelo) {
        return "redirect:/Autos/listar";
    }

    @GetMapping("/usuario/agregar")
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
        modelo.addAttribute("usuario", new Cliente());

        return "usuario/agregar";
    }

    @PostMapping("/usuario/agregar")
    public String agregar(Model modelo, @ModelAttribute Cliente cliente, @RequestParam("clave") String clave,
            HttpSession sesion) {

        boolean res = servicioCliente.agregar(cliente, clave, sesion);
        if (res) {
            sesion.setAttribute("mensajeCLI", servicioCliente.getMensaje());
            return "redirect:/usuario/validar";
        }
        modelo.addAttribute("error", servicioCliente.getMensaje());
        return "usuario/agregar";
    }

    @GetMapping("/usuario/salir")
    public String salir(HttpSession sesion) {
        SecurityContextHolder.clearContext();
        sesion.invalidate();
        return "redirect:/";
    }
}