package es.sixshop.apirest.controller;

import java.util.Collection;

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

import com.fasterxml.jackson.annotation.JsonView;

import es.sixshop.apirest.detail.UserAPIDetail;
import es.sixshop.model.User;
import es.sixshop.security.jwt.AuthResponse;
import es.sixshop.security.jwt.AuthResponse.Status;
import es.sixshop.security.jwt.LoginRequest;
import es.sixshop.security.jwt.UserLoginService;
import es.sixshop.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserRestController {

	@Autowired
	private UserService userS;

	@Autowired
	private UserLoginService userService;

	@JsonView(UserAPIDetail.class)
	@GetMapping("/users/")
	public Collection<User> getUsers() {
		return userS.findAll();
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@CookieValue(name = "accessToken", required = false) String accessToken,
			@CookieValue(name = "refreshToken", required = false) String refreshToken,
			@RequestBody LoginRequest loginRequest) {
		return userService.login(loginRequest, accessToken, refreshToken);
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
