package br.com.simplecrud.servico;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AutenticacaoServ {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
