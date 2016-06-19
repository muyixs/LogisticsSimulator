package orderprocessor.priority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import exceptions.InvalidParamException;
import exceptions.facility.FacilityDoesNotExistException;
import exceptions.item.ItemDoesNotExist;
import facility.record.LogisticRecord;
import managers.facility.FacilityManager;
import managers.item.ItemManager;
import network.NetworkProcessor;
import order.Order;

public class CostPriorityImpl implements Priority {

	final int DAILY_TRAVEL_COST = 500;
	private FacilityManager facilityManager;
	private NetworkProcessor network;
	private ItemManager itemManager;

	@Override
	public Map<String, ArrayList<LogisticRecord>> processOrder(Order order, FacilityManager facilityManager,
			NetworkProcessor network, ItemManager itemManager)
					throws InvalidParamException, FacilityDoesNotExistException, ItemDoesNotExist {
		if (order == null) {
			throw new InvalidParamException("Null Order(order) passed into CostPriorityImpl.processOrder()");
		}
		if (facilityManager == null) {
			throw new InvalidParamException(
					"Null FacilityManager(facilityManager) passed into CostPriorityImpl.processOrder()");
		}
		if (network == null) {
			throw new InvalidParamException(
					"Null NetworkProcessor(network) passed into CostPriorityImpl.processOrder()");
		}
		if (itemManager == null) {
			throw new InvalidParamException(
					"Null ItemManager(itemManager) passed into CostPriorityImpl.processOrder()");
		}
		this.facilityManager = facilityManager;
		this.network = network;
		this.itemManager = itemManager;
		// this.itemManager = itemManager;

		Map<String, ArrayList<LogisticRecord>> solutions = new HashMap<String, ArrayList<LogisticRecord>>();
		for (String item : order.getListOfItems().keySet()) {
			ArrayList<LogisticRecord> finalRecords = new ArrayList<LogisticRecord>();
			int totalQuantity = order.getListOfItems().get(item);
			ArrayList<LogisticRecord> facilityRecords = generateRecords(order, totalQuantity, item);
			sort(facilityRecords);
			while (totalQuantity > 0) {
				if (facilityRecords.size() == 0) {
					System.out.printf(
							"No more quantity available to satisfy Item:%s with remainder of %d in order %s; Item Back-ordered.\n\n",
							item, totalQuantity, order.getId());
					break;
				} else {
					LogisticRecord record = facilityRecords.get(0);
					if (record.getQuantity() >= totalQuantity) {
						String facility = record.getFacilityName();
						facilityManager.reduceFacilityItemCount(facility, record.getItem(), totalQuantity);
						facilityManager.updateSchedule(facility, totalQuantity, record.getProcessingStart());
						int itemCost = itemManager.getCost(record.getItem()) * totalQuantity;
						double facilityCost = facilityManager.getFacilityCost(facility) * facilityManager
								.numOfDaystoProcess(facility, totalQuantity, record.getProcessingStart());
						int transportCost = DAILY_TRAVEL_COST
								* (int) (network.cost(facility, order.getDestination()) + 0.5);
						double totalCost = (itemCost + facilityCost + transportCost);
						record.setTotalCost(totalCost);
						record.setPercentageOfQuantity(record.getQuantity(), order.getListOfItems().get(item));
						record.setQuantity(totalQuantity);
						record.setOrderQuantity(order.getListOfItems().get(item));
						finalRecords.add(record);
						facilityRecords.remove(0);
						totalQuantity = 0;
					} else {
						int facilityQuantity = record.getQuantity();
						totalQuantity -= facilityQuantity;
						String facility = record.getFacilityName();
						facilityManager.updateFacilityItemCount(facility, record.getItem(), 0);
						facilityManager.updateSchedule(facility, facilityQuantity, record.getProcessingStart());
						int itemCost = itemManager.getCost(record.getItem()) * facilityQuantity;
						double facilityCost = facilityManager.getFacilityCost(facility) * facilityManager
								.numOfDaystoProcess(facility, facilityQuantity, record.getProcessingStart());
						int transportCost = DAILY_TRAVEL_COST
								* (int) (network.cost(facility, order.getDestination()) + 0.5);
						double totalCost = (itemCost + facilityCost + transportCost);
						record.setTotalCost(totalCost);
						record.setQuantity(facilityQuantity);
						record.setOrderQuantity(order.getListOfItems().get(item));
						record.setPercentageOfQuantity(facilityQuantity, order.getListOfItems().get(item));
						finalRecords.add(record);
						facilityRecords.remove(0);
						facilityRecords = generateRecords(order, totalQuantity, item);
						sort(facilityRecords);
					}
				}
			}
			solutions.put(item, finalRecords);
			// System.out.println(item);
			// for (int i = 0; i < finalRecords.size(); i++)
			// System.out.println(finalRecords.get(i).getFacilityName());
		}
		return solutions;

	}

	private ArrayList<LogisticRecord> generateRecords(Order order, int totalQuantity, String item)
			throws InvalidParamException, FacilityDoesNotExistException, ItemDoesNotExist {
		Map<String, LogisticRecord> facilitiesWithItemRecords = facilityManager.getFacilityRecords(item, totalQuantity,
				order.getTime());
		ArrayList<LogisticRecord> facilityRecords = new ArrayList<LogisticRecord>();
		if (facilitiesWithItemRecords.size() == 0) {
			// System.out.printf("No facilities possess item %s \n\n", item);
		} else {
			for (String facility : facilitiesWithItemRecords.keySet()) {
				LogisticRecord record = facilitiesWithItemRecords.get(facility);
				int travelDays = (int) (network.cost(facility, order.getDestination()) + 0.5);
				int travelStart = facilitiesWithItemRecords.get(facility).getTravelStart();
				int travelEnd = travelStart + travelDays;
				record.setTravelEnd(travelEnd);
				int itemCost = itemManager.getCost(record.getItem()) * record.getQuantity();
				double facilityCost = facilityManager.getFacilityCost(facility) * facilityManager
						.numOfDaystoProcess(facility, record.getQuantity(), record.getProcessingStart());
				int transportCost = DAILY_TRAVEL_COST * (int) (network.cost(facility, order.getDestination()) + 0.5);
				double totalCost = (itemCost + facilityCost + transportCost);
				record.setTotalCost(totalCost);
			}
			for (LogisticRecord record : facilitiesWithItemRecords.values()) {
				facilityRecords.add(record);
			}
		}
		return facilityRecords;
	}

	private void sort(ArrayList<LogisticRecord> facilityRecords) {
		Collections.sort(facilityRecords, new Comparator<LogisticRecord>() {
			@Override
			public int compare(LogisticRecord r1, LogisticRecord r2) {
				if (r1.getTotalCost() > r2.getTotalCost())
					return 1;
				if (r1.getTotalCost() < r2.getTotalCost())
					return -1;
				return 0;
			}
		});
	}

}
