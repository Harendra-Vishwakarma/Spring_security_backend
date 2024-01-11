package com.securty.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securty.constants.CommonErrorCode;
import com.securty.dto.AuthResponse;
import com.securty.dto.LoginDto;
import com.securty.dto.RefreshTokenDto;
import com.securty.dto.UserDto;
import com.securty.exception.CustomException;
import com.securty.jwt.JwtServiceImpl;
import com.securty.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtServiceImpl jwtServiceImpl;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/register")
	public boolean saveUser(@RequestBody UserDto userDto) {
		return userService.addUser(userDto);
	}

	@PostMapping("/login")
	public AuthResponse login(@Valid @RequestBody LoginDto loginDto) {
		if (userService.getUser(loginDto.getUserName()).isEmpty()) {
			throw new CustomException(CommonErrorCode.INVALID_USER.getDescription());
		}
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
		if (authentication.isAuthenticated()) {
			return new AuthResponse(jwtServiceImpl.generateToken(loginDto.getUserName(), false),
					jwtServiceImpl.generateToken(loginDto.getUserName(), true), null,loginDto.getUserName());
		} else {
			throw new CustomException("invalid user credentials");
		}

	}

	@PostMapping("/refreshToken")
	public ResponseEntity<?> refreshAndGetAuthenticationToken(
			@Valid @RequestBody RefreshTokenDto tokenRefreshRequestDto) {
		try {
			final String requestRefreshToken = tokenRefreshRequestDto.getRefreshToken();
			if (requestRefreshToken.isBlank()) {
				throw new CustomException(CommonErrorCode.REFRESH_TOKEN_CAN_NOT_BE_EMPTY.getDescription());
			}
			final String username = jwtServiceImpl.getUsernameFromToken(requestRefreshToken);
			final UserDetails jwtUser = userDetailsService.loadUserByUsername(username);
			if (jwtUser == null) {
				throw new CustomException(CommonErrorCode.INVALID_REFRESH_TOKEN.getDescription());
			}
			final String token = jwtServiceImpl.refreshToken(requestRefreshToken, false);
			final String refreshToken = jwtServiceImpl.refreshToken(requestRefreshToken, true);
			return ResponseEntity.ok(new AuthResponse(token, refreshToken, null,username));
		} catch (final ExpiredJwtException e) {
			throw new ExpiredJwtException(null, null, CommonErrorCode.INVALID_REFRESH_TOKEN.getDescription());
		} catch (final Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/name")
	public String getUserName() {
		return "Harendra Vishwakarma";
	}

	@GetMapping("/names")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String getUsersName() {
		return "Harendra Vishwakarma";
	}

	@GetMapping("/name/list")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<String> getUserNameList() {
		return Arrays.asList("Vikash", "Suresh", "Dinesh");
	}
}
