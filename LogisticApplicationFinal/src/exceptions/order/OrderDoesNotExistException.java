package exceptions.order;

public class OrderDoesNotExistException extends Exception {
	public OrderDoesNotExistException(String msg) {
		super(msg);
	}
}
