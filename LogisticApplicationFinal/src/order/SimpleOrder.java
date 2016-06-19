package order;

import java.util.HashMap;
import java.util.Map;

import exceptions.InvalidParamException;

public class SimpleOrder implements Order {
	private String id;
	private int time;
	private String destination;
	private String priority;
	private final Map<String, Integer> ListOfItems = new HashMap<String, Integer>();

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) throws InvalidParamException {
		if (id == null) {
			throw new InvalidParamException("Null string(id) passed into SimpleOrder.setID(String)");
		}
		this.id = id;
	}

	@Override
	public int getTime() {
		return time;
	}

	@Override
	public void setTime(int time) throws InvalidParamException {
		if (time < 1) {
			throw new InvalidParamException(
					(time == 0 ? "Zero" : "Negative") + " int(time) passed into  SimpleOrder.setID(String)");
		}
		this.time = time;
	}

	@Override
	public String getDestination() {
		return destination;
	}

	@Override
	public void setDestination(String destination) throws InvalidParamException {
		if (destination == null) {
			throw new InvalidParamException("Null string(destination) passed into SimpleOrder.setDestination(String)");
		}
		this.destination = destination;
	}

	@Override
	public String getPriority() {
		return priority;
	}

	@Override
	public void setPriority(String priority) throws InvalidParamException {
		if (priority == null) {
			throw new InvalidParamException("Null string(priority) passed into SimpleOrder.setpriority(String)");
		}
		this.priority = priority;
	}

	@Override
	public Map<String, Integer> getListOfItems() {
		return ListOfItems;
	}

	@Override
	public void addItem(String item, int quantity) throws InvalidParamException {
		if (item == null) {
			throw new InvalidParamException("Null string(id) passed into SimpleOrder.addItem(String,int)");
		}
		if (quantity < 0) {
			throw new InvalidParamException("Negative int(quantity) passed into SimpleOrder.addItem(String)");
		}
		ListOfItems.put(item, quantity);
	}

	@Override
	public String displayOrder() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Order ID:       %s \n", this.getId()));
		sb.append(String.format("Order Time:     %d \n", this.getTime()));
		sb.append(String.format("Destination:    %s \n", this.getDestination()));
		sb.append(String.format("A Priority:     %s \n", this.getPriority()));
		sb.append("A List of Order Items: \n\n");
		for (String item : this.getListOfItems().keySet()) {
			sb.append(String.format("    Item ID:  %s,  Quantity:  %d \n", item, this.getListOfItems().get(item)));
		}
		sb.append("\n");
		return sb.toString();
	}
}
