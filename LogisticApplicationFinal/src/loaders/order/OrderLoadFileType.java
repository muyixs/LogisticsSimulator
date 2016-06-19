package loaders.order;
import java.util.Map;

import order.Order;

public interface OrderLoadFileType {
	Map<String, Order> loadFile(String OrderFile);
}
