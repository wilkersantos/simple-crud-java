package br.com.simplecrud.dominio.excecoes;

public class CpfInvalidoException extends IllegalArgumentException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CpfInvalidoException() {
		super("Cpf Inválido!!");
	}
}
