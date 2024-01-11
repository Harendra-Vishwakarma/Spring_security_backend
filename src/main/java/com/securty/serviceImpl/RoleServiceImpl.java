package com.securty.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.securty.model.Role;
import com.securty.repository.RoleRepository;
import com.securty.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role getRole(String roleName) {
		Optional<Role> roleOpt = roleRepository.findByRole(roleName);
		if (roleOpt.isPresent()) {
			return roleOpt.get();
		} else {
			throw new UsernameNotFoundException("Role not found");
		}

	}

}
