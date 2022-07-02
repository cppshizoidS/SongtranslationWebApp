package ru.app.ccfit.cppshiz.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import ru.app.ccfit.cppshiz.auth_security.payload.request.LoginRequest;
import ru.nsu.ccfit.cppshiz.auth_security.payload.request.SignupRequest;
import ru.nsu.ccfit.cppshiz.auth_security.payload.response.JwtResponse;
import ru.nsu.ccfit.cppshiz.auth_security.payload.response.MessageResponse;
import ru.nsu.ccfit.cppshiz.auth_security.services.UserInfoService;
import ru.nsu.ccfit.cppshiz.auth_security.userinfo.UserInfo;
import ru.nsu.ccfit.cppshiz.auth_security.utils.JwtUtils;
import ru.nsu.ccfit.cppshiz.model.ERole;
import ru.nsu.ccfit.cppshiz.model.Role;
import ru.nsu.ccfit.cppshiz.model.User;
import ru.nsu.ccfit.cppshiz.repo.RoleRepository;
import ru.nsu.ccfit.cppshiz.repo.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
    
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
    
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest.getLogin() + " " + loginRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtUtils.generateJwtToken(authentication);
		
	    UserInfo userDetails = (UserInfo) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getName(),
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByLogin(signUpRequest.getLogin())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already in use!"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		// Create new user's account
		User user = new User(signUpRequest.getName(), 
							 signUpRequest.getLogin(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getEmail());

		System.out.println("EMAIL : " + user.getEmail() + "\n Password : " + user.getPassword());
		Set<String> strRoles = signUpRequest.getRole();
		HashSet<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByRoleName(ERole.ROLE_TRANSLATOR	)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "mod":
					Role adminRole = roleRepository.findByRoleName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByRoleName(ERole.ROLE_TRANSLATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRole(roles.iterator().next());
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}