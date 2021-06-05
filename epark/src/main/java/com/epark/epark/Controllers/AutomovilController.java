package com.epark.epark.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpSession;

import com.epark.epark.Modelo.Automovil;
import com.epark.epark.Modelo.Cliente;
import com.epark.epark.Servicios.ClienteServicios;
import com.epark.epark.Servicios.ServicioAutomovil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AutomovilController {
    @Autowired
    private ServicioAutomovil servicioAutomovil;

    @Autowired
    private ClienteServicios servicioCliente;

    private Cliente currentUser;

    private Cliente ObtenerUsuario(HttpSession sesion) {
        try {
            int iduser = Integer.parseInt(sesion.getAttribute("iduser").toString());
            Cliente usuario = servicioCliente.FindById((iduser));
            if (usuario == null || usuario.getIdCliente() == 0 || iduser == 0) {
                return null;
            }
            return usuario;
        } catch (Exception e) {
            return null;
        }
    }

    private void currentUser(HttpSession sesion) {
        if (currentUser == null) {
            int iduser = Integer.parseInt(sesion.getAttribute("iduser").toString());
            currentUser = servicioCliente.FindById(iduser);
        }
    }

    @GetMapping("/Autos/listar")
    public String listar(Model modelo, HttpSession sesion) {
        try {
            currentUser(sesion);
            modelo.addAttribute("user", true);
            modelo.addAttribute("nombreU", currentUser.getUsuario());
            Iterable<Automovil> autos = servicioAutomovil.autosClientes(sesion);
            if (autos.iterator().hasNext()) {
                modelo.addAttribute("autos", autos);
                modelo.addAttribute("error", sesion.getAttribute("mensaje"));
            } else {
                modelo.addAttribute("error", "No hay autos registrados para este usuario");
            }
            modelo.addAttribute("auto", new Automovil());
            modelo.addAttribute("error", sesion.getAttribute("mensaje"));
            return "Autos/listar";

        } catch (Exception e) {
            modelo.addAttribute("error", "No hay autos registrados para este usuario");
            return "Autos/listar";
        }
    }

    @GetMapping("/Autos/agregar")
    public String Agregar(Model model, HttpSession sesion) {

        currentUser(sesion);
        model.addAttribute("user", true);
        model.addAttribute("nombreU", currentUser.getUsuario());
        model.addAttribute("modelo", new Automovil());
        model.addAttribute("accion", "agregar");
        sesion.setAttribute("mensaje", servicioAutomovil.getMensaje());
        return "Autos/save";

    }

    @PostMapping("/Autos/agregar")
    public String agregar(Model model, @ModelAttribute Automovil auto, HttpSession sesion) {

        try {
            Cliente usuario = ObtenerUsuario(sesion);

            if (usuario != null) {
                auto.setCliente(usuario);
                servicioAutomovil.agregar(auto);
            }
            sesion.setAttribute("mensaje", servicioAutomovil.getMensaje());
            return "redirect:/Autos/listar";
        } catch (Exception e) {
            sesion.setAttribute("mensaje", servicioAutomovil.getMensaje());
            return "redirect:/Autos/listar";
        }

    }

    @GetMapping("/Autos/editar/{id}")
    public String editar(@PathVariable("id") int idautomovil, Model mp, HttpSession sesion) {
        try {
            currentUser(sesion);
            mp.addAttribute("user", true);
            mp.addAttribute("nombreU", currentUser.getUsuario());

            mp.addAttribute("modelo", servicioAutomovil.buscar(idautomovil));
            mp.addAttribute("accion", "/actualizar");
            sesion.setAttribute("mensaje", servicioAutomovil.getMensaje());
            return "Autos/save";
        } catch (Exception e) {
            mp.addAttribute("user", false);
            return "redirect:/";
        }
    }

    @PostMapping("/actualizar")
    public String actualizar(@Validated Automovil automovil, BindingResult binding, ModelMap mp, HttpSession sesion) {

        if (binding.hasErrors()) {
            return "Autos/listar";
        }

        Cliente usuario = ObtenerUsuario(sesion);
        if (usuario == null || usuario.getIdCliente() == 0) {
            mp.addAttribute("mensaje", "Error al recuperar los datos.Intente nuevamente");
            return "redirect:/Autos/listar";
        }
        automovil.setCliente(usuario);

        servicioAutomovil.editar(automovil);
        mp.put("vehiculo", automovil);
        mp.addAttribute("user", true);
        sesion.setAttribute("mensaje", servicioAutomovil.getMensaje());
        return "redirect:/Autos/listar";
    }
}
