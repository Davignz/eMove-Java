package br.com.fiap.exceptions;


public class PassageiroNaoEncontradoException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Construtor que recebe uma mensagem de erro personalizada
    public PassageiroNaoEncontradoException(String message) {
        super(message); // Passa a mensagem para a classe Exception
    }

    // Construtor que também aceita uma causa (outra exceção)
    public PassageiroNaoEncontradoException(String message, Throwable cause) {
        super(message, cause); // Passa a mensagem e a causa para a classe Exception
    }

    // Construtor padrão, caso nenhuma mensagem seja fornecida
    public PassageiroNaoEncontradoException() {
        super("Passageiro não encontrado."); // Mensagem padrão
    }
}
