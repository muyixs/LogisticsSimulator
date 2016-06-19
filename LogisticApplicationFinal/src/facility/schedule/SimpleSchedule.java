package facility.schedule;

import java.util.HashMap;
import java.util.Map;

import exceptions.InvalidParamException;

public class SimpleSchedule implements Schedule {
	private int rate;
	private int days;
	private final Map<Integer, Integer> facilitySchedule = new HashMap<Integer, Integer>();

	@Override
	public void setRate(int rate) {
		this.rate = rate;
	}

	@Override
	public void setDays(int days) throws InvalidParamException {
		if (days < 1) {
			throw new InvalidParamException(
					(days == 0 ? "Zero" : "Negative") + " int(days) passed into  SimpleSchedule.setDays(String)");
		}
		this.days = days;
		for (int temp = 1; temp <= days; temp++) {
			facilitySchedule.put(temp, rate);
		}
	}

	@Override
	public void processOrder(int quantity, int start) throws InvalidParamException {
		if (quantity < 1) {
			throw new InvalidParamException((quantity == 0 ? "Zero" : "Negative")
					+ " int(quantity) passed into  SimpleOrder.processOrder(int)");
		}
		if (start < 1) {
			throw new InvalidParamException(
					(days == 0 ? "Zero" : "Negative") + " int(start) passed into  SimpleSchedule.processOrder(int)");
		}
		for (int i = start; i <= days; i++) {
			int oldInput = facilitySchedule.get(i);
			if (oldInput == 0) {
				continue;
			} else if (oldInput >= quantity) {
				int newInput = oldInput - quantity;
				facilitySchedule.replace(i, oldInput, newInput);
				break;
			} else {
				quantity -= oldInput;
				facilitySchedule.replace(i, oldInput, 0);
			}
		}

	}

	@Override
	public int processOrderforLogisticRecord(int quantity, int start) throws InvalidParamException {
		if (quantity < 1) {
			throw new InvalidParamException((quantity == 0 ? "Zero" : "Negative")
					+ " int(quantity) passed into  SimpleOrder.processOrderforLogisticRecord(int)");
		}
		if (start < 1) {
			throw new InvalidParamException((days == 0 ? "Zero" : "Negative")
					+ " int(start) passed into  SimpleSchedule.processOrderforLogisticRecord(int)");
		}
		for (int i = start; i <= days; i++) {
			int oldInput = facilitySchedule.get(i);
			if (oldInput == 0) {
				continue;
			} else if (oldInput >= quantity) {
				return i;
			} else {
				quantity -= oldInput;
			}
		}
		System.out.println("All days are booked");
		return 0;
	}

	@Override
	public double numberofDaysToProcess(int quantity, int start) throws InvalidParamException {
		if (quantity < 1) {
			throw new InvalidParamException((quantity == 0 ? "Zero" : "Negative")
					+ " int(quantity) passed into  SimpleSchedule.numberofDaysToProcess(int)");
		}
		if (start < 1) {
			throw new InvalidParamException((days == 0 ? "Zero" : "Negative")
					+ " int(start) passed into  SimpleSchedule.numberofDaysToProcess(int)");
		}
		double numOfDays = 0;
		for (int i = start; i <= days; i++) {
			int oldInput = facilitySchedule.get(i);
			if (oldInput == 0) {
				continue;
			} else if (oldInput >= quantity) {
				double old = oldInput;
				double quant = quantity;
				numOfDays += (quant / old);
				return numOfDays;
			} else {
				numOfDays++;
				quantity -= oldInput;
			}
		}
		// System.out.println("All days are booked");
		return 0;
	}

	@Override
	public int getFirstAvailableDay() {
		for (int i = 1; i <= days; i++) {
			if (facilitySchedule.get(i) > 0) {
				return i;
			}
		}
		System.out.println("No open days");
		return 0;
	}

	@Override
	public String displaySchedule() {
		StringBuilder sb = new StringBuilder();
		for (Integer day : facilitySchedule.keySet()) {
			sb.append(String.format("Day Number: %d, Items that can be processed:  %d \n", day,
					facilitySchedule.get(day)));
		}
		return sb.toString();

	}

}
