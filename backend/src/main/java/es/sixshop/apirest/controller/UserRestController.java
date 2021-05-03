package es.sixshop.apirest.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.security.Principal;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;


import es.sixshop.apirest.detail.ProductOwnerAPIDetail;
import es.sixshop.model.User;
import es.sixshop.repository.UserRepository;
import es.sixshop.security.jwt.AuthResponse;
import es.sixshop.security.jwt.AuthResponse.Status;
import es.sixshop.service.UserService;
import es.sixshop.security.jwt.LoginRequest;
import es.sixshop.security.jwt.UserLoginService;

@RestController
@RequestMapping("/api/auth")
public class UserRestController {

	private Logger log = LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
	private UserLoginService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userS;

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
	
	@JsonView(ProductOwnerAPIDetail.class)
	@PostMapping("/sign_in") //NEW user
	public ResponseEntity<User> createUser(HttpServletRequest request, @RequestBody User user){
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		String encodedPassword = encoder.encode("pass");
		
		 
		 String pass = encodedPassword.substring(8,encodedPassword.length());
		
		
		
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
