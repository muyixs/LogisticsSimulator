package managers.order;

import exceptions.InvalidParamException;
import exceptions.order.OrderMangerTypeDoesNotExist;

public class OrderManagerFactory {
	public OrderManager getOrderManager(String OrderManagerType)
			throws InvalidParamException, OrderMangerTypeDoesNotExist {
		if (OrderManagerType == null) {
			throw new InvalidParamException(
					"Null String(OrderManagerType) passed into OrderManagerFactory.getOrderManager(String)");
		} else if (OrderManagerType.equalsIgnoreCase("Simple")) {
			return SimpleOrderManager.getInstance();
		}
		throw new OrderMangerTypeDoesNotExist(
				"No implementation exists for String(OrderManagerType) in OrderManagerFactory.getOrderManager(String):"
						+ OrderManagerType);
	}
}
