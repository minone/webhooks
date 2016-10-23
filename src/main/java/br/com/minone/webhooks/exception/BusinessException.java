package br.com.minone.webhooks.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 5909050505781615661L;

	public BusinessException(String message) {
        super(message);
    }
}
