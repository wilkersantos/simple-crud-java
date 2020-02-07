package br.com.simplecrud.api.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SegurancaWebConf extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService service;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf().disable().authorizeRequests()
				.antMatchers("/cadastro/**").authenticated()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().permitAll()
				.and()
				
				.addFilterBefore(new JWTLoginFiltro("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				
				.addFilterBefore(new JWTFiltroAutenticacao(),
						UsernamePasswordAuthenticationFilter.class);
				
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.userDetailsService(service)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
