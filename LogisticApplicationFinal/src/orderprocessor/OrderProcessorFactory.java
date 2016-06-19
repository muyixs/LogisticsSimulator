package orderprocessor;

import exceptions.InvalidParamException;
import exceptions.processor.OrderProcessorTypeDoesNotExist;

public class OrderProcessorFactory {
	public OrderProcessor getOrderProcessor(String OrderProcessorType)
			throws InvalidParamException, OrderProcessorTypeDoesNotExist {
		if (OrderProcessorType == null) {
			throw new InvalidParamException(
					"Null String(OrderProcessorType) passed into OrderProcessorFactory.getOrderProcessor(String)");
		} else if (OrderProcessorType.equalsIgnoreCase("Simple")) {
			return SimpleOrderProcessor.getInstance();
		}
		throw new OrderProcessorTypeDoesNotExist(
				"No implementation exists for String(orderProcessorType) in OrderProcessorFactory.getOrderProccessor(String):"
						+ OrderProcessorType);
	}
}
