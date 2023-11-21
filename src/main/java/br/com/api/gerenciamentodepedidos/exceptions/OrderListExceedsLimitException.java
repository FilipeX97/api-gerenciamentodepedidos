package br.com.api.gerenciamentodepedidos.exceptions;

public class OrderListExceedsLimitException extends RuntimeException {
	
	private static final long serialVersionUID = -9022063184735514007L;

	public OrderListExceedsLimitException(String msg) {
		super(msg);
	}
	
	public OrderListExceedsLimitException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
