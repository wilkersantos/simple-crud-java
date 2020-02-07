package br.com.simplecrud.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.simplecrud.dominio.Pessoa;
import br.com.simplecrud.dominio.dto.PessoaCadastroDto;
import br.com.simplecrud.dominio.excecoes.PessoaNotFoundException;
import br.com.simplecrud.servico.CadastroServ;

@RestController
@RequestMapping("/cadastro")
public class CadastroRest {

	@Autowired
	private CadastroServ cadastroServ;

	@PostMapping("/cadastrar")
	public void cadastrarPessoa(@RequestBody PessoaCadastroDto pessoa) {		
		this.cadastroServ.cadastrarPessoa(pessoa);
	}

	@DeleteMapping("/remover/{cpf}")
	public void removerPessoa(@PathVariable("cpf") String cpf) {
		this.cadastroServ.removerPessoa(cpf);
	}

	@PutMapping("/atualizar")
	public void atualizarPessoa(@RequestBody PessoaCadastroDto pessoa) {
		this.cadastroServ.atualizarPessoa(pessoa);
	}

	@GetMapping("/listar")
	public List<Pessoa> listarTodasAsPessoas() {
		return this.cadastroServ.listarTodasAsPessoas();
	}
	
	@GetMapping("/buscar/{cpf}")
	public Pessoa buscarPessoa(@PathVariable("cpf") String cpf) throws PessoaNotFoundException {
		return this.cadastroServ.buscarPessoa(cpf);
	}
}
