package managers.order;

import java.util.ArrayList;
import java.util.Map;

import exceptions.InvalidParamException;
import exceptions.order.OrderDoesNotExistException;
import loaders.order.OrderLoaderInterface;
import order.Order;

public interface OrderManager {

	void setListOfOrders(OrderLoaderInterface orderLoader) throws InvalidParamException;

	String displayOrder(String id) throws InvalidParamException, OrderDoesNotExistException;

	String getId(String order) throws InvalidParamException, OrderDoesNotExistException;

	int getTime(String order) throws InvalidParamException, OrderDoesNotExistException;

	String getPriority(String order) throws InvalidParamException, OrderDoesNotExistException;

	String getDestination(String order) throws InvalidParamException, OrderDoesNotExistException;

	Map<String, Integer> getItems(String order) throws InvalidParamException, OrderDoesNotExistException;

	Map<String, Order> getOrders();

	ArrayList<Order> sortOrders();

}
