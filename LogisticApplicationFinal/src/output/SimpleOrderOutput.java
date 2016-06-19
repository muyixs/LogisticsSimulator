package output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import exceptions.InvalidParamException;
import exceptions.order.OrderDoesNotExistException;
import facility.record.LogisticRecord;
import managers.facility.FacilityManager;
import network.NetworkProcessor;
import order.Order;
import orderprocessor.OrderProcessor;

public class SimpleOrderOutput implements Output {

	private OrderProcessor processor;
	private HashMap<String, Map<String, ArrayList<LogisticRecord>>> solutions;
	private HashMap<String, Boolean> printed;

	private void setSolutions(HashMap<String, Map<String, ArrayList<LogisticRecord>>> solutions) {
		this.solutions = solutions;
	}

	@Override
	public void setOrderOutput(OrderProcessor processor) throws InvalidParamException {
		if (processor == null) {
			throw new InvalidParamException(
					"Null OrderProcessor(processor) passed into SimpleOrderOutput.setOrderOutput()()");
		}
		this.processor = processor;
	}

	@Override
	public void displayOutput() throws InvalidParamException, OrderDoesNotExistException {
		StringBuilder sb = new StringBuilder();
		this.setSolutions(processor.getSolutions());
		int count = 1;
		printed = new HashMap<String, Boolean>();
		ArrayList<Order> sortedOrders = processor.getSortedOrders();
		for (Order orderX : sortedOrders) {
			String order = orderX.getId();
			Map<String, ArrayList<Number>> processingOutput = new HashMap<String, ArrayList<Number>>();
			sb.append("\nOrder #" + count + "\n\n");
			sb.append(processor.getOrderManager().displayOrder(order));
			sb.append("Order Items Logistic Records\n\n");
			Map<String, ArrayList<LogisticRecord>> itemSolutions = solutions.get(order);
			for (String item : itemSolutions.keySet()) {
				ArrayList<Number> itemOutput = new ArrayList<Number>();
				int totalItemQuantity = processor.getOrderManager().getItems(order).get(item);
				double totalCost = 0;
				ArrayList<LogisticRecord> records = itemSolutions.get(item);
				for (LogisticRecord record : records) {
					totalCost += record.getTotalCost();
				}
				itemOutput.add(totalItemQuantity);
				itemOutput.add(totalCost);

				sb.append(String.format("Item ID: %s, Quantity: %d, Cost: $%d\n\n", item, totalItemQuantity,
						Math.round(totalCost)));
				sb.append("Item Arrivals\n\n");
				if (records.size() == 0) {
					sb.append(String.format("    No arrivals for Item: %s\n", item));
				}
				double currentPercentageOfTotal = 0;
				for (LogisticRecord record : records) {
					int arrivalDay = record.getTravelEnd();
					int quantity = record.getQuantity();
					double percentageOfQuantity = record.getPercentageOfQuantity();
					currentPercentageOfTotal += percentageOfQuantity;
					sb.append(String.format("      Day %d:  %d( %.2f%s, %.2f%s) of total\n", arrivalDay, quantity,
							percentageOfQuantity, "%", currentPercentageOfTotal, "%"));
				}
				sb.append("\nLogistic Details:\n\n");
				if (records.size() == 0) {
					sb.append(String.format("    No Logistic details for Item: %s\n\n", item));
				}
				int facilityCount = 0;
				for (LogisticRecord record : records) {
					facilityCount++;
					String facilityName = record.getFacilityName();
					double cost = record.getTotalCost();
					int quantity = record.getQuantity();
					int processingStart = record.getProcessingStart();
					int processingEnd = record.getProcessingEnd();
					int travelEnd = record.getTravelEnd();
					int travelStart = record.getTravelStart();
					sb.append(String.format("%d) Name: %s  (%d of %d)\n", facilityCount, facilityName, quantity,
							totalItemQuantity));
					sb.append(String.format("    Cost: $%d\n", Math.round(cost)));
					sb.append(String.format("    Processing Start: Day %d\n", processingStart));
					sb.append(String.format("    Processing End:   Day %d\n", processingEnd));
					sb.append(String.format("    Travel Start:     Day %d\n", travelStart));
					sb.append(String.format("    Travel End:       Day %d\n", travelEnd));
					sb.append(String.format("    Arrival Day:      Day %d\n\n", travelEnd));
					// facilityCount++;
				}
				itemOutput.add(facilityCount);
				// int itemFirstDay = getItemFirstDay(order, item);
				// if (itemFirstDay == -1){

				// }
				itemOutput.add(getItemFirstDay(order, item));
				itemOutput.add(getItemLastDay(order, item));
				processingOutput.put(item, itemOutput);
			}
			sb.append("Processing Solution:\n\n");
			sb.append(String.format("Order Id: %s\n", order));
			sb.append(String.format("Destination: %s\n", processor.getOrderManager().getDestination(order)));
			sb.append(String.format("Priority: %s\n", processor.getOrderManager().getPriority(order)));
			Double orderCost = 0.0;
			for (ArrayList<Number> itemOutput : processingOutput.values()) {
				orderCost += (Double) itemOutput.get(1);
			}
			sb.append(String.format("Total Cost: $%d\n", Math.round(orderCost)));
			sb.append(String.format("First Delivery Day: %d\n", getOrderFirstDay(order)));
			sb.append(String.format("Last Delivery Day: %d\n", getOrderLastDay(order)));
			sb.append("Order Items: \n\n");
			for (String item : processingOutput.keySet()) {
				ArrayList<Number> itemOutput = processingOutput.get(item);
				int quantity = (Integer) itemOutput.get(0);
				double cost = (Double) itemOutput.get(1);
				int numSources = (Integer) itemOutput.get(2);
				int firstDay = (Integer) itemOutput.get(3);
				int lastDay = (Integer) itemOutput.get(4);
				sb.append(String.format(
						"   Item ID: %s   Quantity: %d   Cost: $%d    Num.Sources: %d   First Day: %d   LastDay: %d\n",
						item, quantity, Math.round(cost), numSources, firstDay, lastDay));
			}
			count++;
		}
		System.out.println(sb.toString());
	}

	private int getItemLastDay(String orderIn, String itemIn) {
		ArrayList<Integer> lastDays = new ArrayList<Integer>();
		for (String order : solutions.keySet()) {
			if (order.equalsIgnoreCase(orderIn)) {
				Map<String, ArrayList<LogisticRecord>> itemSolutions = solutions.get(order);
				for (String item : itemSolutions.keySet()) {
					if (item.equalsIgnoreCase(itemIn)) {
						if (itemSolutions.get(item).isEmpty()) {
							if (printed.containsKey(itemIn) == false) {
								System.out.printf(
										"No facilities currently possess any amount of Item: %s to satisfy Order: %s.\n\n",
										item, order);
								System.out.println("Hence no solution exists for this item. \n");
								printed.put(item, true);
							}
							return 0;
						}
						for (LogisticRecord record : itemSolutions.get(item)) {
							lastDays.add(record.getTravelEnd());
						}

					}
				}
			}
		}
		Collections.sort(lastDays);
		return lastDays.get(lastDays.size() - 1);
	}

	private int getItemFirstDay(String orderIn, String itemIn) {
		ArrayList<Integer> firstDays = new ArrayList<Integer>();
		for (String order : solutions.keySet()) {
			if (order.equalsIgnoreCase(orderIn)) {
				Map<String, ArrayList<LogisticRecord>> itemSolutions = solutions.get(order);
				for (String item : itemSolutions.keySet()) {

					if (item.equalsIgnoreCase(itemIn)) {
						if (itemSolutions.get(item).isEmpty()) {
							if (printed.containsKey(itemIn) == false) {
								System.out.printf(
										"No facilities currently possess any amount of Item: %s to satisfy Order: %s.\n\n",
										item, order);
								System.out.println("Hence no solution exists for this item. \n");
								printed.put(item, true);
							}
							return 0;
						}
						for (LogisticRecord record : itemSolutions.get(item)) {

							// System.out.println(record.getTravelEnd());
							firstDays.add(record.getTravelEnd());
						}

					}
				}
			}

		}
		Collections.sort(firstDays);
		// System.out.println("itemFirstYEAH1");
		// System.out.println(firstDays.size());
		// System.out.println(orderIn);
		// System.out.println(itemIn);
		// System.out.println("itemFirstYEAH2");
		return firstDays.get(0);
	}

	private int getOrderFirstDay(String orderIn) {
		ArrayList<Integer> firstDays = new ArrayList<Integer>();
		for (String order : solutions.keySet()) {
			if (order.equalsIgnoreCase(orderIn)) {
				if (solutions.get(order).isEmpty()) {
					System.out.printf(
							"No facilitity possesses any item in Order: %s. Hence no solution exists for this Order \n\n",
							order);
					return 0;

				}
				Map<String, ArrayList<LogisticRecord>> itemSolutions = solutions.get(order);
				for (String item : itemSolutions.keySet()) {
					int itemFirstDay = getItemFirstDay(orderIn, item);
					if (itemFirstDay == 0) {
						continue;
					}
					firstDays.add(getItemFirstDay(orderIn, item));
				}
			}
		}
		Collections.sort(firstDays);
		return firstDays.get(0);
	}

	private int getOrderLastDay(String orderIn) {
		ArrayList<Integer> lastDays = new ArrayList<Integer>();
		for (String order : solutions.keySet()) {
			if (order.equalsIgnoreCase(orderIn)) {
				if (solutions.get(order).isEmpty()) {
					System.out.printf(
							"No facilitity possesses any item in Order:. Hence no solution exists for this Order %s \n\n",
							order);
					return 0;

				}
				Map<String, ArrayList<LogisticRecord>> itemSolutions = solutions.get(order);
				for (String item : itemSolutions.keySet()) {
					int itemLastDay = getItemLastDay(orderIn, item);
					if (itemLastDay == 0) {
						continue;
					}
					lastDays.add(getItemLastDay(orderIn, item));
				}
			}
		}
		Collections.sort(lastDays);
		return lastDays.get(lastDays.size() - 1);
	}

	@Override
	public void setFacilityOutput(FacilityManager manager, NetworkProcessor network) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayName() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayLinks() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayDepletedItems() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displaySchedule() {
		// TODO Auto-generated method stub

	}

}
