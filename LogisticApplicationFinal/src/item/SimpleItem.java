package item;

import exceptions.InvalidParamException;

public class SimpleItem implements Item {
	private String id;
	private int cost;

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
	public int getCost() {
		return cost;
	}

	@Override
	public void setCost(int cost) throws InvalidParamException {
		if (cost < 0) {
			throw new InvalidParamException("Negative int(cost) passed into SimpleItem.setCost(int)");
		}
		this.cost = cost;
	}
}
