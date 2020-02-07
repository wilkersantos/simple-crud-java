package br.com.simplecrud.servico;

import java.util.List;

import br.com.simplecrud.dominio.Pessoa;
import br.com.simplecrud.dominio.dto.PessoaCadastroDto;
import br.com.simplecrud.dominio.excecoes.PessoaNotFoundException;

public interface CadastroServ {

	void cadastrarPessoa(PessoaCadastroDto novaPessoa);
	
	void removerPessoa(String cpf);
	
	void atualizarPessoa(PessoaCadastroDto novosDados);
	
	List<Pessoa> listarTodasAsPessoas();
	
	Pessoa buscarPessoa(String cpf) throws PessoaNotFoundException;
}
