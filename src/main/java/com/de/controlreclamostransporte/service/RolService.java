package com.de.controlreclamostransporte.service;
import com.de.controlreclamostransporte.entity.Roles;
import com.de.controlreclamostransporte.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Roles> getAllRoles() {
        return rolRepository.findAll();
    }

    public Roles save(Roles rol) {
        return rolRepository.save(rol);
    }

    public Optional<Roles> findRolesByIds(Long roleId) {
        return rolRepository.findById(roleId);
    }
}