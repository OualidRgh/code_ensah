package com.exception;

/**
 * Classe d'exception qui pr�sente l'erreur d'un compte introuvable
 * 
 * @author boudaa
 *
 */
public class CompteNotFoundException extends Exception {

	public CompteNotFoundException() {
	}

	public CompteNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompteNotFoundException(String message) {
		super(message);
	}

}
