package es.sixshop.apirest.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.sixshop.model.User;
import es.sixshop.repository.UserRepository;
import es.sixshop.security.jwt.AuthResponse;
import es.sixshop.security.jwt.AuthResponse.Status;
import es.sixshop.security.jwt.LoginRequest;
import es.sixshop.security.jwt.UserLoginService;

@RestController
@RequestMapping("/api/auth")
public class UserRestController {

	@Autowired
	private UserLoginService userService;
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@CookieValue(name = "accessToken", required = false) String accessToken,
			@CookieValue(name = "refreshToken", required = false) String refreshToken,
			@RequestBody LoginRequest loginRequest) {
		return userService.login(loginRequest, accessToken, refreshToken);
	}

	@GetMapping("/me")
	public ResponseEntity<User> me(HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		
		if(principal != null) {
			return ResponseEntity.ok(userRepository.findByNickname(principal.getName()).orElseThrow());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refreshToken(
			@CookieValue(name = "refreshToken", required = false) String refreshToken) {
		return userService.refresh(refreshToken);
	}

	@PostMapping("/logout")
	public ResponseEntity<AuthResponse> logOut(HttpServletRequest request, HttpServletResponse response) {
		return ResponseEntity.ok(new AuthResponse(Status.SUCCESS, userService.logout(request, response)));
	}
}
