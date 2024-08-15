package com.de.controlreclamostransporte.controller;
import com.de.controlreclamostransporte.entity.Roles;
import com.de.controlreclamostransporte.entity.Usuario;
import com.de.controlreclamostransporte.service.RolService;
import com.de.controlreclamostransporte.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RolService rolService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "message", required = false) String message,
                        Model model) {
        if (error != null) {
            model.addAttribute("error", message != null ? message : "Nombre de usuario o contraseña incorrectos.");
        }
        return "login";
    }

    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        List<Roles> roles = rolService.getAllRoles();
        model.addAttribute("roles", roles);
        return "registro";
    }

    @PostMapping("/registro")
    public String registro(@ModelAttribute Usuario usuario, @RequestParam("roleIds") Long roleId, Model model) {
        logger.info("inicia el registro");
        try {
            logger.info("Verifica si el username ya existe");
            if (userService.existsByUsername(usuario.getUsername())) {
                model.addAttribute("error", "El nombre de usuario ya existe. Por favor elige otro.");
                model.addAttribute("usuario", usuario);
                model.addAttribute("roles", rolService.getAllRoles());
                return "registro";
            }

            logger.info("Asigna el rol seleccionado al usuario");
            Optional<Roles> roleOpt = rolService.findRolesByIds(roleId);
            if (roleOpt.isPresent()) {
                usuario.setRoles(Set.of(roleOpt.get()));
            } else {
                model.addAttribute("error", "El rol seleccionado no es válido.");
                model.addAttribute("usuario", usuario);
                model.addAttribute("roles", rolService.getAllRoles());
                return "registro";
            }

            logger.info("Guarda el usuario");
            Usuario savedUser = userService.saveUsuario(usuario);
            if (savedUser != null && savedUser.getId() != null) {
                logger.info("redirect a login");
                return "redirect:/login";
            } else {
                logger.info("Error al guardar el usuario.");
                throw new Exception("Error al guardar el usuario.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al registrar el usuario. Inténtalo nuevamente.");
            model.addAttribute("usuario", usuario);
            model.addAttribute("roles", rolService.getAllRoles());
            return "registro";
        }
    }

    @GetMapping("/")
    public String home() {
        return "home/index";
    }
}