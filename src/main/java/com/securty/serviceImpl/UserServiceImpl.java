package com.securty.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.securty.dto.UserDto;
import com.securty.model.Role;
import com.securty.model.User;
import com.securty.repository.UserRepository;
import com.securty.service.RoleService;
import com.securty.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleService roleService;

	@Override
	public Optional<User> getUser(String userName) {

		return userRepository.findByName(userName);
	}

	@Override
	public List<String> getUserNameList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(UserDto userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Set<Role> roles=new HashSet<>();
		User user=userDto.getUser();
		roles.add(roleService.getRole("USER"));
		user.setRoles(roles);
		userRepository.save(user);
		return true;
	}

}
