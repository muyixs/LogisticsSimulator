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

public interface OrderProcessor {

	void processOrders(FacilityManager facilityManager, NetworkProcessor network, ItemManager itemManager,
			OrderManager orderManager) throws PriorityImplementationDoesNotExistException, InvalidParamException,
					FacilityDoesNotExistException, ItemDoesNotExist;

	OrderManager getOrderManager();

	HashMap<String, Map<String, ArrayList<LogisticRecord>>> getSolutions();

	ArrayList<Order> getSortedOrders();

}
