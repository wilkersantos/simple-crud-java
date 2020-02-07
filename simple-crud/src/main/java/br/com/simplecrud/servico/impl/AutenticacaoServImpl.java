package br.com.simplecrud.servico.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.simplecrud.dominio.Usuario;
import br.com.simplecrud.repositorio.UsuarioRepository;
import br.com.simplecrud.servico.AutenticacaoServ;

@Service
public class AutenticacaoServImpl implements AutenticacaoServ, UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(this.usuarioRepository.count() == 0) {
			Usuario admin = new Usuario();
			admin.setUsername("admin");
			admin.setPassword(new BCryptPasswordEncoder().encode("password"));
			
			this.usuarioRepository.save(admin);
		}
		
		UserDetails userDetails = null;
		Optional<Usuario> usuarioRetornado = this.usuarioRepository.findById(username);
		
		if(usuarioRetornado.isPresent()) {
			Usuario uUax = usuarioRetornado.get();
			
			List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
			
			userDetails = new User(uUax.getUsername(), uUax.getPassword(), true,
					true, true, true, authorities);
		}else {
			throw new UsernameNotFoundException("Usuário não encontrado!!");
		}
		
		return userDetails;
	}

}
