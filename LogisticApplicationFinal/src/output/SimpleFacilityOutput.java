package output;

import java.util.ArrayList;

import exceptions.InvalidParamException;
import exceptions.facility.FacilityDoesNotExistException;
import managers.facility.FacilityManager;
import network.NetworkProcessor;
import orderprocessor.OrderProcessor;

public class SimpleFacilityOutput implements Output {

	private FacilityManager manager;
	private NetworkProcessor network;

	@Override
	public void setFacilityOutput(FacilityManager manager, NetworkProcessor network) throws InvalidParamException {
		this.manager = manager;
		this.network = network;
		if (manager == null) {
			throw new InvalidParamException(
					"Null FacilityManager(manager) passed into SimpleFacilityOutput.setFacilityOutput()");
		}
		if (network == null) {
			throw new InvalidParamException(
					"Null NetworkProcessor(network) passed into SimpleFacilityOutput.setFacilityOutput()");
		}
	}

	@Override
	public void displayOutput() throws InvalidParamException, FacilityDoesNotExistException {
		for (String facility : network.getFacilitiesList()) {
			System.out.println(facility + "\n");
			StringBuilder sb = new StringBuilder();
			sb.append("Direct Links: ");
			ArrayList<String> links = (ArrayList<String>) network.getLinks(facility);
			for (String link : links) {
				sb.append(String.format("%s (%.2f);", link, network.cost(facility, link)));
			}
			System.out.println(sb.toString());
			System.out.println();
			System.out.println("Active Inventory: \n");
			System.out.println(manager.displayFacilityInventory(facility));
			System.out.println("Depleted Items: \n");
			System.out.println(manager.displayDepletedItems(facility));
			System.out.println(manager.displayFacilitySchedule(facility));
		}
	}

	@Override
	public void displayName() {

	}

	@Override
	public void displayLinks() {

	}

	@Override
	public void displayInventory() {

	}

	@Override
	public void displayDepletedItems() {

	}

	@Override
	public void displaySchedule() {

	}

	@Override
	public void setOrderOutput(OrderProcessor processor) {
		// TODO Auto-generated method stub

	}
}
