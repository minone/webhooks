package br.com.minone.webhooks.exception;

public class SecurityException extends RuntimeException {

	private static final long serialVersionUID = 102305622472970350L;

	public SecurityException(String message) {
        super(message);
    }
}
