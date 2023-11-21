package br.com.api.gerenciamentodepedidos.exceptions;

public class ExceededQuantityException extends RuntimeException {
	
	private static final long serialVersionUID = 8887356549463534150L;

	public ExceededQuantityException(String msg) {
		super(msg);
	}
	
	public ExceededQuantityException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
