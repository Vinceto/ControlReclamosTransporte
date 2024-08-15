package com.de.controlreclamostransporte.controller;
import com.de.controlreclamostransporte.entity.Roles;
import com.de.controlreclamostransporte.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RoleController {

    @Autowired
    private RolService rolService;

    @GetMapping("/roles")
    public String listRoles(Model model) {
        model.addAttribute("roles", rolService.getAllRoles());
        return "roles/list";
    }

    @GetMapping("/roles/new")
    public String createRoleForm(Model model) {
        model.addAttribute("rol", new Roles());
        return "roles/create";
    }

    @PostMapping("/roles")
    public String saveRole(Roles rol) {
        rolService.save(rol);
        return "redirect:/roles";
    }
}