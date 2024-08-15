package com.de.controlreclamostransporte.repository;

import com.de.controlreclamostransporte.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByNombre(String nombre);
}