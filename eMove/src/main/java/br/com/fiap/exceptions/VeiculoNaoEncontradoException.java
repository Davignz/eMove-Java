package br.com.fiap.exceptions;

public class VeiculoNaoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Construtor que recebe uma mensagem de erro personalizada
    public VeiculoNaoEncontradoException(String message) {
        super(message); // Passa a mensagem para a classe Exception
    }

    // Construtor que também aceita uma causa (outra exceção)
    public VeiculoNaoEncontradoException(String message, Throwable cause) {
        super(message, cause); // Passa a mensagem e a causa para a classe Exception
    }

    // Construtor padrão, caso nenhuma mensagem seja fornecida
    public VeiculoNaoEncontradoException() {
        super("Passageiro não encontrado."); // Mensagem padrão
    }

}
