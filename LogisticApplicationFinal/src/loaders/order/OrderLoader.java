package loaders.order;

import java.util.Map;

import exceptions.InvalidParamException;
import loaders.Loader;
import order.Order;

public class OrderLoader implements Loader, OrderLoaderInterface {
	private OrderLoadFileType loadbyFileType;
	private Map<String, Order> orders;

	private void setFileTypeLoader(String fileName) throws InvalidParamException {
		String extension = "";
		if (fileName == null) {
			throw new InvalidParamException("Null string(fileName) passed into OrderLoader.setFileTypeLoader(String)");
		}
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}
		if (extension.equalsIgnoreCase("xml")) {
			this.loadbyFileType = new OrderLoadXMLImpl();
		} else {
			this.loadbyFileType = new OrderLoadNullImpl();
		}
	}

	@Override
	public void load(String file) throws InvalidParamException {
		setFileTypeLoader(file);
		setOrders(loadbyFileType.loadFile(file));
	}

	@Override
	public void load(String file, String inventory) {

	}

	@Override
	public Map<String, Order> getOrders() {
		return orders;
	}

	private void setOrders(Map<String, Order> orders) throws InvalidParamException {
		if (orders == null) {
			throw new InvalidParamException("Null Order(orders) passed into OrderLoader.setOrders(Map<String, Order>)");
		}
		this.orders = orders;
	}
}
