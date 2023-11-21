package br.com.api.gerenciamentodepedidos.exceptions;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 6635386255945639972L;

	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
