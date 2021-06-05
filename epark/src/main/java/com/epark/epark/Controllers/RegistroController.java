package com.epark.epark.Controllers;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.servlet.http.HttpSession;

import com.epark.epark.Modelo.Automovil;
import com.epark.epark.Modelo.Cliente;
import com.epark.epark.Modelo.Registro;
import com.epark.epark.Servicios.ClienteServicios;
import com.epark.epark.Servicios.ServicioAutomovil;
import com.epark.epark.Servicios.ServicioRegistro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {
    @Autowired
    private ServicioRegistro servicioRegistro;

    @Autowired
    private ClienteServicios servicioCliente;

    @Autowired
    private ServicioAutomovil servicioAutomovil;

    private Cliente currentUser;

    private Iterable<Automovil> obtenerListaAutomovil(HttpSession session) {
        try {
            return servicioAutomovil.autosClientes(session);
        } catch (Exception e) {
            return null;
        }
    }

    private Automovil obtenerAutomovil(int id) {
        try {
            return servicioAutomovil.buscar(id);
        } catch (Exception e) {
            return null;
        }
    }

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
            int iduser = Integer.parseInt(sesion.getAttribute("iduser").toString());
            currentUser = servicioCliente.FindById(iduser);
    }

    @GetMapping("/Reserva/Listar")
    public String Listar(Model modelo, HttpSession sesion) {
        try {
            int iduser = Integer.parseInt(sesion.getAttribute("iduser").toString());
            if (servicioAutomovil.IsthereCar(iduser)) {

                Iterable<Registro> registros = servicioRegistro.ListarRegistros(iduser);

                if (registros.iterator().hasNext()) {
                    modelo.addAttribute("registros", registros);
                    modelo.addAttribute("error", sesion.getAttribute("mensajeReg"));
                } else {
                    modelo.addAttribute("error", "No hay reservas hechas para este usuario");
                }
            } else {
                sesion.setAttribute("mensaje", "Debe agregar antes un automovil para hacer una reserva");
                return "redirect:/Autos/listar";
            }

            currentUser(sesion);
            modelo.addAttribute("user", true);
            modelo.addAttribute("nombreU", currentUser.getUsuario());

        } catch (Exception e) {
            modelo.addAttribute("error", sesion.getAttribute("mensajeReg"));
        }
        return "Reserva/Listar";
    }

    @GetMapping("/Reserva/Agregar")
    public String Agregar(Model model, HttpSession session) {
        try {
            int iduser = Integer.parseInt(session.getAttribute("iduser").toString());
            if (servicioAutomovil.IsthereCar(iduser) == false) {
                session.setAttribute("mensaje",
                        "Ultimo aviso: Antes de hacer una reservación debe agregar un automovil");
                return "redirect:/Autos/listar";
            }
            if (servicioRegistro.IsActiveRegistro(iduser)) {
                session.setAttribute("mensajeReg", "Ultimo aviso: Ya hay una reserva activa para este usuario ");
                return "redirect:/Reserva/Error";
            }
            currentUser(session);
            model.addAttribute("user", true);
            model.addAttribute("nombreU", currentUser.getUsuario());
            Iterable<Automovil> autom = obtenerListaAutomovil(session);
            model.addAttribute("marcas", autom);
            Calendar cls = Calendar.getInstance();
            String temp = String.valueOf(cls.get(Calendar.YEAR)) + "-" + String.valueOf(cls.get(Calendar.MONTH)) + "-"
                    + String.valueOf(cls.get(Calendar.DAY_OF_MONTH)) + "T" + String.valueOf(cls.get(Calendar.HOUR))
                    + ":" + String.valueOf(Calendar.MINUTE);

            model.addAttribute("tiempo", temp);
            model.addAttribute("reserva", new Registro());

        } catch (Exception e) {
            model.addAttribute("error", e.getCause());
        }
        return "Reserva/Agregar";
    }

    @PostMapping("/Reserva/Agregar")
    public String Agregar(Model model, HttpSession sesion, @RequestParam("idautomovil") int idautomovil,
            @RequestParam("fechaIngreso") String fechaIngreso, @RequestParam("horaEntrada") String horaEntrada,
            @RequestParam("fechaSalida") String fechaSalida, @RequestParam("horaSalida") String horaSalida) {
        DateTimeFormatter fechaParse = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter horaParse = DateTimeFormatter.ofPattern("HH:mm");

        try {
            Cliente usuario = ObtenerUsuario(sesion);
            LocalDate fechaingresoDate = LocalDate.parse(fechaIngreso, fechaParse);
            LocalDate fechasalidaDate = LocalDate.parse(fechaSalida, fechaParse);
            LocalTime horaingresoTime = LocalTime.parse(horaEntrada, horaParse);
            LocalTime horasalidaTime = LocalTime.parse(horaSalida, horaParse);

            int dif = Period.between(fechaingresoDate, fechasalidaDate).getDays() * 1440;
            long seg = Duration.between(horaingresoTime, horasalidaTime).getSeconds() / 60;

            long tiempo_total = dif + seg;
            long pago = (long) (tiempo_total * 0.25);

            Registro reg = new Registro();

            reg.setAutomovil(obtenerAutomovil(idautomovil));
            reg.setCliente(usuario);
            reg.setFechaIngreso(fechaingresoDate);
            reg.setFechaSalida(fechasalidaDate);
            reg.setHoraEntrada(horaingresoTime);
            reg.setHoraSalida(horasalidaTime);
            reg.setMontoAPagar(BigDecimal.valueOf(pago));
            reg.setEstatus("A");

            servicioRegistro.agregar(reg);
            sesion.setAttribute("mensajeReg", "Ultimo aviso: " + servicioRegistro.getMensaje());

        } catch (Exception e) {
            e.getMessage();
        }

        return "redirect:/Reserva/Listar";

    }

    @GetMapping("/Reserva/Cancelar/{id}")
    public String eliminar(Model model, HttpSession sesion, @PathVariable("id") int id) {
        try {
            servicioRegistro.Cancelar(id);
            sesion.setAttribute("mensajeReg", "Ultimo aviso" + servicioRegistro.getMensaje());
            return "redirect:/Reserva/Listar";
        } catch (Exception e) {
            sesion.setAttribute("mensajeReg", "Ultimo aviso" + servicioRegistro.getMensaje());
            return "redirect:/Reserva/Listar";
        }
    }

    @GetMapping("/Reserva/Error")
    public String error(HttpSession sesion, Model model) {
        try {
            int iduser = Integer.parseInt(sesion.getAttribute("iduser").toString());
            currentUser(sesion);
            model.addAttribute("user", true);
            model.addAttribute("nombreU", currentUser.getUsuario());
            model.addAttribute("message", sesion.getAttribute("mensajeReg"));
            return "Reserva/Error";

        } catch (Exception e) {
            model.addAttribute("mensajeReg", e.getCause());
            return "Reserva/Error";
        }
    }
}
