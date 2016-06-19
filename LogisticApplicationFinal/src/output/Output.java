package output;

import exceptions.InvalidParamException;
import exceptions.facility.FacilityDoesNotExistException;
import exceptions.order.OrderDoesNotExistException;
import managers.facility.FacilityManager;
import network.NetworkProcessor;
import orderprocessor.OrderProcessor;

public interface Output {
	void setFacilityOutput(FacilityManager manager, NetworkProcessor network) throws InvalidParamException;

	void displayName();

	void displayLinks();

	void displayInventory();

	void displayDepletedItems();

	void displaySchedule();

	void displayOutput() throws InvalidParamException, FacilityDoesNotExistException, OrderDoesNotExistException;

	void setOrderOutput(OrderProcessor processor) throws InvalidParamException;
}
