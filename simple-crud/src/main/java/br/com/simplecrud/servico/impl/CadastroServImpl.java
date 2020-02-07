package br.com.simplecrud.servico.impl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.simplecrud.dominio.Pessoa;
import br.com.simplecrud.dominio.dto.PessoaCadastroDto;
import br.com.simplecrud.dominio.excecoes.CpfInvalidoException;
import br.com.simplecrud.dominio.excecoes.EmailInvalinoException;
import br.com.simplecrud.dominio.excecoes.PessoaNotFoundException;
import br.com.simplecrud.repositorio.PessoaRepository;
import br.com.simplecrud.servico.CadastroServ;

@Service
public class CadastroServImpl implements CadastroServ{

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public void cadastrarPessoa(PessoaCadastroDto novaPessoa) {
		Pessoa pessoa = new Pessoa(novaPessoa);
		if(!validarCpf(pessoa.getCpf()))
			throw new CpfInvalidoException();
		if(!validarEmail(novaPessoa.getEmail()))
			throw new EmailInvalinoException();
		if(!this.pessoaRepository.existsById(novaPessoa.getCpf())) 
			this.pessoaRepository.save(pessoa);	
	}
	
	public void removerPessoa(String cpf) {
		this.pessoaRepository.deleteById(cpf);
	}
	
	public void atualizarPessoa(PessoaCadastroDto novosDados) {
		Optional<Pessoa> dadosRetornados = pessoaRepository.findById(novosDados.getCpf());
		if(dadosRetornados.isPresent()) {
			Pessoa dadosParaAtualizar = dadosRetornados.get();
			dadosParaAtualizar.atualizarPessoa(novosDados);
			this.pessoaRepository.save(dadosParaAtualizar);
		}
	}
	
	public List<Pessoa> listarTodasAsPessoas() {
		return this.pessoaRepository.findAll();
	}
	
	public Pessoa buscarPessoa(String cpf) throws PessoaNotFoundException {
		Optional<Pessoa> pessoa = this.pessoaRepository.findById(cpf);
	
		if(pessoa.isPresent()) {
			return pessoa.get();
		} else {
			throw new PessoaNotFoundException();
		}
	}
	
	private static boolean validarCpf(String cpf) {

		if (cpf.equals("00000000000") ||
				cpf.equals("11111111111") ||
				cpf.equals("22222222222") || cpf.equals("33333333333") ||
				cpf.equals("44444444444") || cpf.equals("55555555555") ||
				cpf.equals("66666666666") || cpf.equals("77777777777") ||
				cpf.equals("88888888888") || cpf.equals("99999999999") ||
				(cpf.length() != 11))
			return(false);

		char dig10, dig11;
		int sm, i, r, num, peso;


		try {

			sm = 0;
			peso = 10;
			for (i=0; i<9; i++) {              

				num = (int)(cpf.charAt(i) - 48); 
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else dig10 = (char)(r + 48); 

			sm = 0;
			peso = 11;
			for(i=0; i<10; i++) {
				num = (int)(cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else dig11 = (char)(r + 48);

			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
				return(true);
			else return(false);
		} catch (InputMismatchException erro) {
			return(false);
		}
	}
	private static boolean validarEmail(String email) { 
		boolean isEmailIdValid = false; 
		if (email != null && email.length() > 0) { 
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"; 
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE); 
			Matcher matcher = pattern.matcher(email); 
			if (matcher.matches()) { 
				isEmailIdValid = true; 
			} 
		} 
		return isEmailIdValid; }
}
