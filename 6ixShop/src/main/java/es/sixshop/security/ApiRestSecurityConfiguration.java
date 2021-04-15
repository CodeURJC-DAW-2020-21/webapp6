package es.sixshop.security;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.sixshop.security.jwt.JwtRequestFilter;

@Configuration
@Order(1)
public class ApiRestSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private RepositoryUserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	//Expose AuthenticationManager as a Bean to be used in other services
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Private endpoints
		 http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/profile/shopping").hasAnyRole("USER","ADMIN");
		 http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/profile/**").hasAnyRole("USER","ADMIN");
		 http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/carts/").hasAnyRole("USER","ADMIN");
		 http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/products/{idProduct}").hasAnyRole("USER","ADMIN");
		    
		 http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/carts/{idProduct}").hasAnyRole("USER","ADMIN");
		 
		 
		 // Other endpoints are public
		 http.authorizeRequests().anyRequest().permitAll();
		 // Disable CSRF protection (it is difficult to implement in REST APIs)
		 http.csrf().disable();
		 // disable Basic Authentication
		 http.httpBasic().disable();
		 // Disable Form login Authentication
		 http.formLogin().disable();
		 // Avoid creating session (because every request has credentials) 
		 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
		 // Add JWT Token filter
		 http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	

	}
}