package order;

import exceptions.InvalidParamException;
import exceptions.order.OrderTypeDoesNotExistException;

public class OrderFactory {
	public Order getOrder(String OrderType) throws OrderTypeDoesNotExistException, InvalidParamException {
		if (OrderType == null) {
			throw new InvalidParamException("Null String(OrderType) passed into OrderFactory.getOrder(String)");
		} else if (OrderType.equalsIgnoreCase("Simple")) {
			return new SimpleOrder();
		}
		throw new OrderTypeDoesNotExistException(
				"No implementation exists for String(orderType) in OrderFactory.getOrder(String):" + OrderType);
	}
}
