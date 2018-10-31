package com.exception;

/**
 * Classe d'exception qui pr�sente une erreur lors d'une op�ration sur un compte
 * bancaire
 * 
 * @author boudaa
 *
 */
public class CompteOperationException extends Exception {

	public CompteOperationException() {
	}

	public CompteOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompteOperationException(String message) {
		super(message);
	}

}
