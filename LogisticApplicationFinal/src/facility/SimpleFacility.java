package facility;

import exceptions.InvalidParamException;
import exceptions.facility.InventoryTypeDoesNotExistException;
import exceptions.facility.ScheduleTypeDoesNotExistException;
import facility.inventory.Inventory;
import facility.inventory.SimpleInventory;
import facility.record.LogisticRecord;
import facility.record.SimpleLogisticRecord;
import facility.schedule.Schedule;
import facility.schedule.SimpleSchedule;

public class SimpleFacility implements Facility {
	private String name;
	private int cost;
	private int rate;
	private int limit;
	private Inventory facilityInventory;
	private Schedule facilitySchedule;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) throws InvalidParamException {
		if (name == null) {
			throw new InvalidParamException("Null string(name) passed into SimpleFacility.setName(String)");
		}
		this.name = name;
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public void setCost(int cost) throws InvalidParamException {
		if (cost < 1) {
			throw new InvalidParamException(
					(cost == 0 ? "Zero" : "Negative") + " int(cost) passed into  SimpleFacility.setCost(int)");
		}
		this.cost = cost;
	}

	@Override
	public int getRate() {
		return rate;
	}

	@Override
	public void setRate(int rate) throws InvalidParamException {
		if (rate < 1) {
			throw new InvalidParamException(
					(rate == 0 ? "Zero" : "Negative") + " int(rate) passed into  SimpleFacility.setRate(int)");
		}
		this.rate = rate;
		facilitySchedule.setRate(rate);
	}

	@Override
	public LogisticRecord generateFacilityRecord(String item, int quantity, int time) throws InvalidParamException {
		SimpleLogisticRecord facilityRecord = new SimpleLogisticRecord();
		facilityRecord.setFacilityName(this.name);
		facilityRecord.setItem(item);
		facilityRecord.setQuantity(this.facilityInventory.getItemCountForRecord(item, quantity, limit));
		facilityRecord.setProcessingStart(time);
		facilityRecord.setProcessingEnd(
				this.facilitySchedule.processOrderforLogisticRecord(facilityRecord.getQuantity(), time));
		facilityRecord.setTravelStart(facilityRecord.getProcessingEnd() + 1);
		return facilityRecord;
	}

	@Override
	public double numOfDaystoProcess(int quantity, int start) throws InvalidParamException {
		return facilitySchedule.numberofDaysToProcess(quantity, start);
	}

	@Override
	public void setInventory(String type) throws InventoryTypeDoesNotExistException, InvalidParamException {
		if (type == null) {
			throw new InvalidParamException("Null String(type) passed into SimpleFacility.setInventory(String)");
		}
		if (type.equalsIgnoreCase("simple")) {
			facilityInventory = new SimpleInventory();
		} else {
			throw new InventoryTypeDoesNotExistException(
					"No implementation exists for String(type) in SimpleFacility.setInventory(String):" + type);
		}

	}

	@Override
	public void updateSchedule(int quantity, int start) throws InvalidParamException {
		facilitySchedule.processOrder(quantity, start);
	}

	@Override
	public void setSchedule(String type) throws ScheduleTypeDoesNotExistException, InvalidParamException {
		if (type == null) {
			throw new InvalidParamException("Null String(type) passed into SimpleFacility.setSchedule(String)");
		}
		if (type.equalsIgnoreCase("simple")) {
			facilitySchedule = new SimpleSchedule();
		} else {
			throw new ScheduleTypeDoesNotExistException(
					"No implementation exists for String(type) in SimpleFacility.setSchedule(String):" + type);
		}
	}

	@Override
	public void setDays(int days) throws InvalidParamException {
		facilitySchedule.setDays(days);
	}

	@Override
	public String displaySchedule() {
		return facilitySchedule.displaySchedule();
	}

	@Override
	public boolean hasItem(String item) throws InvalidParamException {
		return facilityInventory.hasItem(item);
	}

	@Override
	public void addItem(String item, int count) throws InvalidParamException {
		facilityInventory.addItem(item, count);
	}

	@Override
	public int getItemCount(String item) throws InvalidParamException {
		return facilityInventory.getItemCount(item, limit);
	}

	@Override
	public void updateItemCount(String item, int count) throws InvalidParamException {
		facilityInventory.updateItemCount(item, count);
	}

	@Override
	public void reduceItemCount(String item, int count) throws InvalidParamException {
		facilityInventory.reduceItemCount(item, count);
	}

	@Override
	public void increaseItemCount(String item, int count) throws InvalidParamException {
		facilityInventory.increaseItemCount(item, count);
	}

	@Override
	public String displayInventory() {
		return facilityInventory.toString();
	}

	@Override
	public String displayDepletedItems() {
		return facilityInventory.displayDepletedItems();
	}

	@Override
	public int getLimit() {
		return limit;
	}

	@Override
	public void setLimit(int limit) throws InvalidParamException {
		if (limit < 1) {
			throw new InvalidParamException(
					(limit == 0 ? "Zero" : "Negative") + " int(limit) passed into  SimpleFacility.setLimit(int)");
		}
		this.limit = limit;
	}

}
