package com.project.laporkelas.repository;

import com.project.laporkelas.entity.ERole;
import com.project.laporkelas.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
