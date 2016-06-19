package facility;

import exceptions.InvalidParamException;
import exceptions.facility.InventoryTypeDoesNotExistException;
import exceptions.facility.ScheduleTypeDoesNotExistException;
import facility.record.LogisticRecord;

public interface Facility {
	String getName();

	int getCost();

	int getRate();

	void setName(String name) throws InvalidParamException;

	void setCost(int cost) throws InvalidParamException;

	void setRate(int rate) throws InvalidParamException;

	void setInventory(String type) throws InvalidParamException, InventoryTypeDoesNotExistException;

	void addItem(String item, int count) throws InvalidParamException;

	String displayInventory();

	boolean hasItem(String item) throws InvalidParamException;

	int getItemCount(String item) throws InvalidParamException;

	void updateItemCount(String item, int count) throws InvalidParamException;

	void reduceItemCount(String item, int count) throws InvalidParamException;

	void increaseItemCount(String item, int count) throws InvalidParamException;

	String displayDepletedItems();

	void setSchedule(String type) throws InvalidParamException, ScheduleTypeDoesNotExistException;

	void setDays(int days) throws InvalidParamException;

	String displaySchedule();

	LogisticRecord generateFacilityRecord(String item, int quantity, int start) throws InvalidParamException;

	void updateSchedule(int quantity, int start) throws InvalidParamException;

	double numOfDaystoProcess(int quantity, int start) throws InvalidParamException;

	int getLimit();

	void setLimit(int limit) throws InvalidParamException;
}
