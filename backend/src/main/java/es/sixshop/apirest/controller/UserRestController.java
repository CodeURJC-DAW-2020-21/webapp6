package es.sixshop.apirest.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.sixshop.apirest.detail.ProductOwnerAPIDetail;
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
	private UserLoginService userService;

	@Autowired
	private UserService userS;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@CookieValue(name = "accessToken", required = false) String accessToken,
			@CookieValue(name = "refreshToken", required = false) String refreshToken,
			@RequestBody LoginRequest loginRequest) {
		return userService.login(loginRequest, accessToken, refreshToken);
	}

	@JsonView(ProductOwnerAPIDetail.class)
	@PostMapping("/sign_in") // NEW user
	public ResponseEntity<User> createUser(HttpServletRequest request, @RequestBody User user) {
		System.out.println("asdasdasdasdasdasdasd");

		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encodedPassword = encoder.encode("pass");

		// PasswordEncoder strongEncoder = new BCryptPasswordEncoder(12);
		/// String encodedPassword = strongEncoder.encode("123");

		String pass = encodedPassword.substring(8, encodedPassword.length());

		user.setEncodedPassword(pass);

		userS.save(user);

		URI location = fromCurrentRequest().path("/{idUser}/").buildAndExpand(user.getIdUser()).toUri();

		return ResponseEntity.created(location).body(user);
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
