package orderprocessor.priority;

import java.util.ArrayList;
import java.util.Map;

import exceptions.InvalidParamException;
import exceptions.facility.FacilityDoesNotExistException;
import exceptions.item.ItemDoesNotExist;
import facility.record.LogisticRecord;
import managers.facility.FacilityManager;
import managers.item.ItemManager;
import network.NetworkProcessor;
import order.Order;

public interface Priority {
	Map<String, ArrayList<LogisticRecord>> processOrder(Order order, FacilityManager facilityManager,
			NetworkProcessor network, ItemManager itemManager)
					throws InvalidParamException, FacilityDoesNotExistException, ItemDoesNotExist;
}
