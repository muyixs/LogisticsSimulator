package exceptions.order;

public class OrderTypeDoesNotExistException extends Exception {

	public OrderTypeDoesNotExistException(String msg) {
		super(msg);
	}
}
