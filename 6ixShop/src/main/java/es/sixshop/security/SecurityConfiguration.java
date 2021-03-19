package es.sixshop.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	/*@Value("${security.user}")
	private String user;
	
	@Value("${security.encodedPassword}")
	private String encodedPassword;*/


	@Autowired
	RepositoryUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
		
		/*PasswordEncoderFactories.createDelegatingPasswordEncoder();

		auth.inMemoryAuthentication()
		.withUser(user).password("{bcrypt}"+encodedPassword).roles("ADMIN");*/
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/sign_in").permitAll();
		http.authorizeRequests().antMatchers("/category").permitAll();
		http.authorizeRequests().antMatchers("/productDetail").permitAll();

		// Private pages (all other pages)
		http.authorizeRequests().antMatchers("/profile").hasAnyRole("USER,ADMIN");
		http.authorizeRequests().antMatchers("/cardPayment").hasAnyRole("USER,ADMIN");
		http.authorizeRequests().antMatchers("/cart").hasAnyRole("USER,ADMIN");
		http.authorizeRequests().antMatchers("/cart/*").hasAnyRole("USER,ADMIN");//  mirar el *
		http.authorizeRequests().antMatchers("/checkout").hasAnyRole("USER,ADMIN");
		http.authorizeRequests().antMatchers("/checkout/*").hasAnyRole("USER,ADMIN");
		http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");

		// Login form
		http.formLogin().loginPage("/login"); //URL formulario Login
		http.formLogin().usernameParameter("nickname"); //Nombre del campo formulario "nickname"
		http.formLogin().passwordParameter("encodedPassword"); //Nombre campo formulario "password"
		http.formLogin().defaultSuccessUrl("/"); //URL para autenticacion correcta
		http.formLogin().failureUrl("/login"); //URL para error en autenticacion

		// Logout
		http.logout().logoutUrl("/header"); //URL para hacer logout
		http.logout().logoutSuccessUrl("/"); //URL a la que navegar cuando se hace logout

		// Disable CSRF at the moment
		http.csrf().disable();

		// Allow H2 console
		http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
		http.headers().frameOptions().sameOrigin();

	}
}
