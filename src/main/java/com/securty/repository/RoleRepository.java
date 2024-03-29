package com.securty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securty.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRole(String role);
}
