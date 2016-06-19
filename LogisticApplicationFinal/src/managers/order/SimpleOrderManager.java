package managers.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import exceptions.InvalidParamException;
import exceptions.order.OrderDoesNotExistException;
import loaders.order.OrderLoaderInterface;
import order.Order;

public class SimpleOrderManager implements OrderManager {
	private Map<String, Order> ListOfOrders;

	private static SimpleOrderManager ourInstance;

	public static SimpleOrderManager getInstance() {
		if (ourInstance == null) {
			ourInstance = new SimpleOrderManager();
		}
		return ourInstance;
	}

	private SimpleOrderManager() {
	}

	@Override
	public void setListOfOrders(OrderLoaderInterface orderLoader) throws InvalidParamException {
		if (orderLoader == null) {
			throw new InvalidParamException(
					"Null OrderLoaderInterface(orderLoader) passed into SimpleOrderManager.getID(OrderLoaderInterface)");
		}
		this.ListOfOrders = orderLoader.getOrders();
	}

	@Override
	public Map<String, Order> getOrders() {
		return ListOfOrders;
	}

	@Override
	public String getId(String order) throws InvalidParamException, OrderDoesNotExistException {
		if (order == null) {
			throw new InvalidParamException("Null string(order) passed into SimpleOrderManager.getID(String)");
		}
		if (ListOfOrders.containsKey(order) == false) {
			throw new OrderDoesNotExistException(
					"Order passed into SimpleOrderManagerManger.getID(String) does not exist:" + order);
		}
		return ListOfOrders.get(order).getId();
	}

	@Override
	public int getTime(String order) throws OrderDoesNotExistException, InvalidParamException {
		if (order == null) {
			throw new InvalidParamException("Null string(order) passed into SimpleOrderManager.getTime(String)");
		}
		if (ListOfOrders.containsKey(order) == false) {
			throw new OrderDoesNotExistException(
					"Order passed into SimpleOrderManagerManger.getTime(String) does not exist:" + order);
		}
		return ListOfOrders.get(order).getTime();
	}

	@Override
	public String getPriority(String order) throws InvalidParamException, OrderDoesNotExistException {
		if (order == null) {
			throw new InvalidParamException("Null string(order) passed into SimpleOrderManager.getPriority(String)");
		}
		if (ListOfOrders.containsKey(order) == false) {
			throw new OrderDoesNotExistException(
					"Order passed into SimpleOrderManagerManger.getPriority(String) does not exist:" + order);
		}
		return ListOfOrders.get(order).getPriority();
	}

	@Override
	public String getDestination(String order) throws InvalidParamException, OrderDoesNotExistException {
		if (order == null) {
			throw new InvalidParamException("Null string(order) passed into SimpleOrderManager.getDestination(String)");
		}
		if (ListOfOrders.containsKey(order) == false) {
			throw new OrderDoesNotExistException(
					"Order passed into SimpleOrderManagerManger.getDestination(String) does not exist:" + order);
		}
		return ListOfOrders.get(order).getDestination();
	}

	@Override
	public Map<String, Integer> getItems(String order) throws InvalidParamException, OrderDoesNotExistException {
		if (order == null) {
			throw new InvalidParamException("Null string(order) passed into SimpleOrderManager.getItems(String)");
		}
		if (ListOfOrders.containsKey(order) == false) {
			throw new OrderDoesNotExistException(
					"Order passed into SimpleOrderManagerManger.getItems(String) does not exist:" + order);
		}
		return ListOfOrders.get(order).getListOfItems();
	}

	@Override
	public String displayOrder(String order) throws InvalidParamException, OrderDoesNotExistException {
		if (order == null) {
			throw new InvalidParamException("Null string(id) passed into SimpleOrderManager.displayOrder(String)");
		}
		if (ListOfOrders.containsKey(order) == false) {
			throw new OrderDoesNotExistException(
					"Order passed into SimpleOrderManagerManger.displayOrder(String) does not exist:" + order);
		}
		return ListOfOrders.get(order).displayOrder();
	}

	@Override
	public ArrayList<Order> sortOrders() {
		ArrayList<Order> orderRecords = new ArrayList<Order>();
		for (String order : ListOfOrders.keySet()) {
			orderRecords.add(ListOfOrders.get(order));
		}

		Collections.sort(orderRecords, new Comparator<Order>() {
			@Override
			public int compare(Order r1, Order r2) {
				if (r1.getTime() > r2.getTime())
					return 1;
				if (r1.getTime() < r2.getTime())
					return -1;
				return 0;
			}
		});
		return orderRecords;
	}

}
