package br.com.simplecrud.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.simplecrud.dominio.Pessoa;

public interface PessoaRepository extends MongoRepository<Pessoa, String>{

}
