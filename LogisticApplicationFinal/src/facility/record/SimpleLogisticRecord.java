package facility.record;

import exceptions.InvalidParamException;

public class SimpleLogisticRecord implements LogisticRecord {

	private String item;
	private String facility;
	private int quantity;
	private int orderQuantity;
	private double percentageOfQuantity;
	private int processingStart;
	private int processingEnd;
	private int travelStart;
	private int travelEnd;
	private double totalCost;

	@Override
	public String getItem() {
		return item;
	}

	@Override
	public void setItem(String item) throws InvalidParamException {
		if (item == null) {
			throw new InvalidParamException("Null string(item) passed into SimpleLogisticRecord.setItem(String)");
		}
		this.item = item;
	}

	@Override
	public String getFacilityName() {
		return facility;
	}

	@Override
	public void setFacilityName(String facility) throws InvalidParamException {
		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleLogisticRecord.setFacilityName(String)");
		}
		this.facility = facility;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public void setQuantity(int quantity) throws InvalidParamException {
		if (quantity < 1) {
			throw new InvalidParamException((quantity == 0 ? "Zero" : "Negative")
					+ " int(quantity) passed into  SimpleLogisticRecord.setQuantity(int)");
		}
		this.quantity = quantity;
	}

	@Override
	public int getProcessingStart() {
		return processingStart;
	}

	@Override
	public void setProcessingStart(int processingStart) throws InvalidParamException {
		if (processingStart < 1) {
			throw new InvalidParamException((quantity == 0 ? "Zero" : "Negative")
					+ " int(processingStart) passed into  SimpleLogisticRecord.setProcessingStart(int)");
		}
		this.processingStart = processingStart;
	}

	@Override
	public int getProcessingEnd() {
		return processingEnd;
	}

	@Override
	public void setProcessingEnd(int processingEnd) throws InvalidParamException {
		if (processingEnd < 1) {
			throw new InvalidParamException((quantity == 0 ? "Zero" : "Negative")
					+ " int(processingEnd) passed into  SimpleLogisticRecord.setProcessingEnd(int)");
		}
		this.processingEnd = processingEnd;
	}

	@Override
	public int getTravelStart() {
		return travelStart;
	}

	@Override
	public void setTravelStart(int travelStart) throws InvalidParamException {
		if (travelStart < 1) {
			throw new InvalidParamException((quantity == 0 ? "Zero" : "Negative")
					+ " int(travelStart) passed into  SimpleLogisticRecord.setTravelStart(int)");
		}
		this.travelStart = travelStart;
	}

	@Override
	public int getTravelEnd() {
		return travelEnd;
	}

	@Override
	public void setTravelEnd(int travelEnd) throws InvalidParamException {
		if (travelEnd < 1) {
			throw new InvalidParamException((quantity == 0 ? "Zero" : "Negative")
					+ " int(travelEnd) passed into  SimpleLogisticRecord.setTravelEnd(int)");
		}
		this.travelEnd = travelEnd;
	}

	@Override
	public double getTotalCost() {
		return totalCost;
	}

	@Override
	public void setTotalCost(double TotalCost) throws InvalidParamException {
		if (TotalCost < 1) {
			throw new InvalidParamException((quantity == 0 ? "Zero" : "Negative")
					+ " double( TotalCost) passed into  SimpleLogisticRecord.setTotalCost(double)");
		}
		this.totalCost = TotalCost;
	}

	@Override
	public int getOrderQuantity() {
		return orderQuantity;
	}

	@Override
	public void setOrderQuantity(int orderQuantity) throws InvalidParamException {
		if (orderQuantity < 1) {
			throw new InvalidParamException((quantity == 0 ? "Zero" : "Negative")
					+ " int(orderQuantity) passed into  SimpleLogisticRecord.setOrderQuantity(int)");
		}
		this.orderQuantity = orderQuantity;
	}

	@Override
	public double getPercentageOfQuantity() {
		return percentageOfQuantity;
	}

	@Override
	public void setPercentageOfQuantity(int quantity, int orderQuantity) throws InvalidParamException {
		if (quantity < 1) {
			throw new InvalidParamException((quantity == 0 ? "Zero" : "Negative")
					+ " int(quantity) passed into  SimpleLogisticRecord.setPercentageOfQuantity(int)");
		}
		if (orderQuantity < 1) {
			throw new InvalidParamException((quantity == 0 ? "Zero" : "Negative")
					+ " int(orderQuantity) passed into  SimpleLogisticRecord.setPercentageOfQuantity(int)");
		}
		this.percentageOfQuantity = ((double) quantity / (double) orderQuantity) * 100;
	}

}
