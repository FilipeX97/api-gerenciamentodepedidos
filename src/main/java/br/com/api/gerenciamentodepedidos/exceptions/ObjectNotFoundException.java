package br.com.api.gerenciamentodepedidos.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6592528160581825522L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
