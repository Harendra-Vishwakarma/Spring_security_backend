package com.securty.service;

import java.util.List;
import java.util.Optional;

import com.securty.dto.UserDto;
import com.securty.model.User;

public interface UserService {

	
	public Optional<User> getUser(String userName);
	
	public List<String> getUserNameList();
	
	public boolean addUser(UserDto userDto);
}
