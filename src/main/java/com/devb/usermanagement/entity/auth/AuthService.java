package com.devb.usermanagement.entity.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devb.usermanagement.entity.Role;
import com.devb.usermanagement.service.JwtService;
import com.devb.usermanagement.service.exception.DataIntegratyViolationException;
import com.devb.usermanagement.service.exception.ObjectNotFoundException;

@Service
public class AuthService {

	private final UserAppRepository userAppRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	public AuthService(UserAppRepository userAppRepository, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, JwtService jwtService) {

		this.userAppRepository = userAppRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	
	public boolean checkUser(UserRegistrationRequest userRegistrationRequest) {
		Optional<UserApp> userApp = userAppRepository.findByEmail(userRegistrationRequest.getEmail());
		Optional<UserApp> useOptional = userAppRepository.findByPassw(userRegistrationRequest.getPassword());

		if (useOptional.isPresent() || userApp.isPresent()) {
			throw new DataIntegratyViolationException("Password or Email already exist");
		}
		return true;
	}

	public UserApp checkEmail(String email) {
		Optional<UserApp> userApp = userAppRepository.findByEmail(email);

		return userApp.orElseThrow(() -> new DataIntegratyViolationException("Email already exist"));
	}

	public UserResponse save(UserRegistrationRequest userRegistrationRequest) {

		checkUser(userRegistrationRequest);
		UserApp uApp = new UserApp();
		uApp.setEmail(userRegistrationRequest.getEmail());
		uApp.setFirstName(userRegistrationRequest.getFirstName());
		uApp.setLastName(userRegistrationRequest.getLastName());
		uApp.setPassw(passwordEncoder.encode(userRegistrationRequest.getPassword()));
		uApp.setRole(Role.USER);
		UserApp respApp = userAppRepository.save(uApp);
		return new UserResponse(respApp.getId(), respApp.getFirstName() + " " + respApp.getLastName(),
				respApp.getEmail(), respApp.getRole().name());
	}

	public LoginResponse login(UserAuthRequest userAuthRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userAuthRequest.getEmail(), userAuthRequest.getPassword()));

		if (authentication.isAuthenticated()) {
			return new LoginResponse(authentication.getName(), jwtService.generateToken(authentication.getName()));
		} else {
			throw new DataIntegratyViolationException("Credentials invalid");
		}
	}

	public List<UserResponse> getAll() {
		List<UserApp> list = userAppRepository.findAll();
		List<UserResponse> listResponse = new ArrayList<>();
		list.forEach(resp -> {
			listResponse.add(new UserResponse(resp.getId(), resp.getFirstName() + " " + resp.getLastName(),
					resp.getEmail(), resp.getRole().name()));
		});
		return listResponse;
	}

	public UserApp findById(Integer id) {
		Optional<UserApp> userOptional = userAppRepository.findById(id);
		return userOptional.orElseThrow(() -> new ObjectNotFoundException("USER not FOUND"));
	}

	public UserResponse updateRole(Integer id) {

		UserApp userApp = findById(id);
		if(userApp.getRole().toString().equals("USER")) {
			userApp.setRole(Role.ADMIN);
		}else {
			userApp.setRole(Role.USER);
		}
		
		UserApp respApp = userAppRepository.save(userApp);
		return new UserResponse(respApp.getId(), respApp.getFirstName()+" "+respApp.getLastName(),
				respApp.getEmail(), respApp.getRole().name());
	}

	public void deleteUser(Integer id) {

		UserApp usOptional = findById(id);
		userAppRepository.delete(usOptional);
		}
}
