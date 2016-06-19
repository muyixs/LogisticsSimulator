package loaders.order;
import java.util.HashMap;
import java.util.Map;

import order.Order;

public class OrderLoadNullImpl implements OrderLoadFileType {
	@Override
	public Map<String, Order> loadFile(String OrderFile) {
		Map<String, Order> Orders = new HashMap<String, Order>();
		System.out.println("Implementation to open this file format does not yet exist.");
		return Orders;
	}
}
