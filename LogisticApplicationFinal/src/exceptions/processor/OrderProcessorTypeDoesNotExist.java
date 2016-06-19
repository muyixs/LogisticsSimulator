package exceptions.processor;

public class OrderProcessorTypeDoesNotExist extends Exception {
	public OrderProcessorTypeDoesNotExist(String msg) {
		super(msg);
	}
}
