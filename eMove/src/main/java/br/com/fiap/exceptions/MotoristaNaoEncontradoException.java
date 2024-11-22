package br.com.fiap.exceptions;

import java.sql.SQLException;

public class MotoristaNaoEncontradoException extends SQLException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Construtor que recebe uma mensagem de erro personalizada
    public MotoristaNaoEncontradoException(String message) {
        super(message); // Passa a mensagem para a classe Exception
    }

    // Construtor que também aceita uma causa (outra exceção)
    public MotoristaNaoEncontradoException(String message, Throwable cause) {
        super(message, cause); // Passa a mensagem e a causa para a classe Exception
    }

    // Construtor padrão, caso nenhuma mensagem seja fornecida
    public MotoristaNaoEncontradoException() {
        super("Passageiro não encontrado."); // Mensagem padrão
    }

}
