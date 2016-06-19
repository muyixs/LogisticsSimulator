package loaders.order;
import java.util.Map;

import order.Order;

public interface OrderLoaderInterface {
	Map<String, Order> getOrders();
}
