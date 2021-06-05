package com.epark.epark.Controllers;

import javax.servlet.http.HttpSession;

import com.epark.epark.Modelo.Cliente;
import com.epark.epark.Servicios.ClienteServicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ClienteServicios serviciocliente;

    private Cliente currentUser;

    @GetMapping("/")
    public String Index(HttpSession session, Model modelo) {
        try {
            currentUser = serviciocliente.FindById(Integer.parseInt(session.getAttribute("iduser").toString()));

            if (currentUser != null) {
                modelo.addAttribute("user", true);
                modelo.addAttribute("nombreU", currentUser.getUsuario());
            }
        } catch (Exception e) {
            modelo.addAttribute("user", false);
        }

        return "Home/Index";
    }	

    @GetMapping("/funcion")
    public String Funcion(HttpSession session, Model modelo) {
        try {
            currentUser = serviciocliente.FindById(Integer.parseInt(session.getAttribute("iduser").toString()));
            if (currentUser != null) {
                modelo.addAttribute("user", true);
                modelo.addAttribute("nombreU", currentUser.getUsuario());
            }
        } catch (Exception e) {
            modelo.addAttribute("user", false);

        }

        return "Home/Funcion";
    }
}
