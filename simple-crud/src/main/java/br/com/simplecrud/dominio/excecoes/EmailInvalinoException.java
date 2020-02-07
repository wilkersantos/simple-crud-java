package br.com.simplecrud.dominio.excecoes;

public class EmailInvalinoException extends IllegalArgumentException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmailInvalinoException() {
		super("Email inválido!!");
	}
 
}
