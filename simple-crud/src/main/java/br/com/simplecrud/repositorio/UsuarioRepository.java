package br.com.simplecrud.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.simplecrud.dominio.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{

}
