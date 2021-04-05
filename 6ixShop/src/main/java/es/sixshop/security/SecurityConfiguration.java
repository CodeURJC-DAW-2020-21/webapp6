package es.sixshop.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	RepositoryUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/sign_in").permitAll();
		http.authorizeRequests().antMatchers("/category").permitAll();
		http.authorizeRequests().antMatchers("/single-product").permitAll();
		http.authorizeRequests().antMatchers("/error").permitAll();

		// Private pages (all other pages)
		http.authorizeRequests().antMatchers("/profile").hasAnyRole("USER,ADMIN");
		http.authorizeRequests().antMatchers("/edit-product").hasAnyRole("USER,ADMIN");
		http.authorizeRequests().antMatchers("/cardPayment").hasAnyRole("USER,ADMIN");
		http.authorizeRequests().antMatchers("/cart").hasAnyRole("USER,ADMIN");
		http.authorizeRequests().antMatchers("/cart/*").hasAnyRole("USER,ADMIN");

		// Login form
		http.formLogin().loginPage("/login"); // URL form Login
		http.formLogin().usernameParameter("nickname"); // Name of the form field "nickname"
		http.formLogin().passwordParameter("encodedPassword"); // Name form field "password"
		http.formLogin().defaultSuccessUrl("/"); // URL for correct authentication
		http.formLogin().failureUrl("/login"); // URL for authentication error

		// Logout
		http.logout().logoutUrl("/header"); // URL to logout
		http.logout().logoutSuccessUrl("/"); // URL to navigate to when logging out

		// Disable CSRF at the moment
		//http.csrf().disable();

		// Allow H2 console
		http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
		http.headers().frameOptions().sameOrigin();

	}
}
