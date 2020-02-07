package br.com.simplecrud.dominio;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.simplecrud.dominio.dto.PessoaCadastroDto;
import br.com.simplecrud.dominio.enumerado.Sexo;

@Document
public class Pessoa {
	
	private String nome;
	
	private String email;
	
	private Date dataNascimento;
	
	private String naturalidade;
	
	private String nacionalidade;
	
	@Id
	private String cpf;
	
	private Sexo sexo;
	
	private Date dataAtualizacao;
	
	public Pessoa() {
		// TODO Auto-generated constructor stub
	}
	
	public Pessoa(PessoaCadastroDto p) {
		this.cpf = p.getCpf();
		this.nome = p.getNome();
		this.email = p.getEmail();
		this.dataNascimento = p.getDataNascimento();
		this.naturalidade = p.getNaturalidade();
		this.nacionalidade = p.getNacionalidade();
		this.sexo = p.getSexo();
		
		this.dataAtualizacao = new Date();
	}
	
	public void atualizarPessoa(PessoaCadastroDto p) {
		this.nome = p.getNome();
		this.email = p.getEmail();
		this.dataNascimento = p.getDataNascimento();
		this.naturalidade = p.getNaturalidade();
		this.nacionalidade = p.getNacionalidade();
		this.sexo = p.getSexo();
		
		this.dataAtualizacao = new Date();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
}
