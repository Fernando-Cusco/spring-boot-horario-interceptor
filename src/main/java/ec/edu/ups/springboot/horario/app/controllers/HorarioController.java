package ec.edu.ups.springboot.horario.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HorarioController {
    @GetMapping({"/index", "/", ""})
    public String index(Model model) {
        model.addAttribute("titulo", "Bienvenido a atención de clientes");
        return "index";
    }

    @GetMapping({"/cerrado"})
    public String cerrado(Model model) {
        model.addAttribute("titulo", "Cerrado actualmente");
        model.addAttribute("horario", "cerrado");
        return "cerrado";
    }
}
