package orderprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import exceptions.InvalidParamException;
import exceptions.facility.FacilityDoesNotExistException;
import exceptions.item.ItemDoesNotExist;
import exceptions.processor.PriorityImplementationDoesNotExistException;
import facility.record.LogisticRecord;
import managers.facility.FacilityManager;
import managers.item.ItemManager;
import managers.order.OrderManager;
import network.NetworkProcessor;
import order.Order;
import orderprocessor.priority.CostPriorityImpl;
import orderprocessor.priority.Priority;
import orderprocessor.priority.TimePriorityImpl;

public class SimpleOrderProcessor implements OrderProcessor {

	// private Map<String, Order> listOfOrders;
	private HashMap<String, Map<String, ArrayList<LogisticRecord>>> orderSolutions;
	private ArrayList<Order> sortedOrders;
	private static SimpleOrderProcessor ourInstance;
	private Priority strategy;
	// private FacilityManager facilityManager;
	// private NetworkProcessor network;
	// private ItemManager itemManager;
	private OrderManager orderManager;

	public static SimpleOrderProcessor getInstance() {
		if (ourInstance == null) {
			ourInstance = new SimpleOrderProcessor();
		}
		return ourInstance;
	}

	private SimpleOrderProcessor() {
	}

	@Override
	public void processOrders(FacilityManager facilityManager, NetworkProcessor network, ItemManager itemManager,
			OrderManager orderManager) throws PriorityImplementationDoesNotExistException, InvalidParamException,
					FacilityDoesNotExistException, ItemDoesNotExist {
		// this.facilityManager = facilityManager;
		// this.network = network;
		// this.itemManager = itemManager;
		this.setOrderManager(orderManager);
		orderSolutions = new HashMap<String, Map<String, ArrayList<LogisticRecord>>>();
		sortedOrders = orderManager.sortOrders();
		for (Order order : sortedOrders) {
			// System.out.println(order.getId());
			if (order.getPriority().equalsIgnoreCase("Time")) {
				setStrategy(new TimePriorityImpl());
				Map<String, ArrayList<LogisticRecord>> hold = executeStrategy(order, facilityManager, network,
						itemManager);
				orderSolutions.put(order.getId(), hold);
			} else if ((order.getPriority().equalsIgnoreCase("Cost"))) {
				setStrategy(new CostPriorityImpl());
				orderSolutions.put(order.getId(), executeStrategy(order, facilityManager, network, itemManager));
			} else {
				throw new PriorityImplementationDoesNotExistException(
						"Implementation for Order Priority does not exist yet.");
			}
		}

	}

	@Override
	public ArrayList<Order> getSortedOrders() {
		return sortedOrders;
	}

	private void setStrategy(Priority strategy) {
		this.strategy = strategy;
	}

	private Map<String, ArrayList<LogisticRecord>> executeStrategy(Order order, FacilityManager facilityManager,
			NetworkProcessor network, ItemManager itemManager)
					throws InvalidParamException, FacilityDoesNotExistException, ItemDoesNotExist {
		return strategy.processOrder(order, facilityManager, network, itemManager);
	}

	@Override
	public OrderManager getOrderManager() {
		return orderManager;
	}

	private void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
		// listOfOrders = orderManager.getOrders();
	}

	@Override
	public HashMap<String, Map<String, ArrayList<LogisticRecord>>> getSolutions() {
		return this.orderSolutions;
	}

}
