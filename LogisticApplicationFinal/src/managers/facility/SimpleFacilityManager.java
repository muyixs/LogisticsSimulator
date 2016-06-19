package managers.facility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.InvalidParamException;
import exceptions.facility.FacilityDoesNotExistException;
import facility.Facility;
import facility.record.LogisticRecord;
import loaders.facility.FacilityLoaderInterface;

public class SimpleFacilityManager implements FacilityManager {

	private Map<String, Facility> ListOfFacilities;
	private static SimpleFacilityManager ourInstance;

	public static SimpleFacilityManager getInstance() {
		if (ourInstance == null) {
			ourInstance = new SimpleFacilityManager();
		}
		return ourInstance;
	}

	private SimpleFacilityManager() {
	}

	@Override
	public List<String> facilitiesWithItem(String item) throws InvalidParamException, FacilityDoesNotExistException {
		List<String> facilityList = new ArrayList<String>();
		for (String facility : ListOfFacilities.keySet()) {
			if (facilityHasItem(facility, item) == true) {
				facilityList.add(facility);
			}
		}
		return facilityList;
	}

	@Override
	public void displayFacilityNames() {
		for (String key : ListOfFacilities.keySet()) {
			System.out.println(key);
		}
	}

	@Override
	public Map<String, LogisticRecord> getFacilityRecords(String item, int quantity, int start)
			throws InvalidParamException {
		Map<String, LogisticRecord> facilityRecords = new HashMap<String, LogisticRecord>();
		for (Facility key : ListOfFacilities.values()) {
			if (key.hasItem(item) == true) {
				facilityRecords.put(key.getName(), key.generateFacilityRecord(item, quantity, start));
			}
		}
		return facilityRecords;
	}

	@Override
	public void setListOfFacilities(FacilityLoaderInterface facilityLoader) throws InvalidParamException {
		if (facilityLoader == null) {
			throw new InvalidParamException(
					"Null ItemLoaderInterface(itemLoader) passed into SimpleFacilityManager.setListOfItems(ItemLoaderInterface)");
		}

		this.ListOfFacilities = facilityLoader.getFacilities();
	}

	@Override
	public String getFacilityName(String facility) throws InvalidParamException, FacilityDoesNotExistException {
		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleFacilityManager.getFacilityName(String)");
		}
		if (ListOfFacilities.containsKey(facility) == false) {
			throw new FacilityDoesNotExistException(
					"String (facility) passed into SimpleFacilityManager.getFacilityName: does not exist" + facility);
		}
		return ListOfFacilities.get(facility).getName();
	}

	@Override
	public int getFacilityLimit(String facility) throws InvalidParamException, FacilityDoesNotExistException {
		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleFacilityManager.getFacilityLimit(String)");
		}
		if (ListOfFacilities.containsKey(facility) == false) {
			throw new FacilityDoesNotExistException(
					"String (facility) passed into SimpleFacilityManager.getFacilityLimit does not exist: " + facility);
		}
		return ListOfFacilities.get(facility).getLimit();
	}

	@Override
	public int getFacilityCost(String facility) throws InvalidParamException, FacilityDoesNotExistException {
		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleFacilityManager.getFacilityCost(String)");
		}
		if (ListOfFacilities.containsKey(facility) == false) {
			throw new FacilityDoesNotExistException(
					"String (facility) passed into SimpleFacilityManager.getFacilityCost does not exist: " + facility);
		}
		return ListOfFacilities.get(facility).getCost();
	}

	@Override
	public int getFacilityRate(String facility) throws InvalidParamException, FacilityDoesNotExistException {
		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleFacilityManager.getFacilityRate(String)");
		}
		if (ListOfFacilities.containsKey(facility) == false) {
			throw new FacilityDoesNotExistException(
					"String (facility) passed into SimpleFacilityManager.getFacilityRate does not exist: " + facility);
		}
		return ListOfFacilities.get(facility).getRate();
	}

	@Override
	public String displayDepletedItems(String facility) throws InvalidParamException, FacilityDoesNotExistException {
		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleFacilityManager.displayDepletedItems(String)");
		}
		if (ListOfFacilities.containsKey(facility) == false) {
			throw new FacilityDoesNotExistException(
					"String (facility) passed into SimpleFacilityManager.displayDepletedItems does not exist: "
							+ facility);
		}
		return ListOfFacilities.get(facility).displayDepletedItems();
	}

	@Override
	public void updateSchedule(String facility, int quantity, int start)
			throws InvalidParamException, FacilityDoesNotExistException {
		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleFacilityManager.updateSchedule(String)");
		}
		if (ListOfFacilities.containsKey(facility) == false) {
			throw new FacilityDoesNotExistException(
					"String (facility) passed into SimpleFacilityManager.updateSchedule does not exist: " + facility);
		}
		ListOfFacilities.get(facility).updateSchedule(quantity, start);
	}

	@Override
	public double numOfDaystoProcess(String facility, int quantity, int start)
			throws InvalidParamException, FacilityDoesNotExistException {
		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleFacilityManager.numOfDaysToProcess(String)");
		}
		if (ListOfFacilities.containsKey(facility) == false) {
			throw new FacilityDoesNotExistException(
					"String (facility) passed into SimpleFacilityManager.numOfDaystoProcess does not exist: "
							+ facility);
		}
		return ListOfFacilities.get(facility).numOfDaystoProcess(quantity, start);
	}

	@Override
	public String displayFacilityInventory(String facility)
			throws InvalidParamException, FacilityDoesNotExistException {
		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleFacilityManager.displayFacilityInventory(String)");
		}
		if (ListOfFacilities.containsKey(facility) == false) {
			throw new FacilityDoesNotExistException(
					"String (facility) passed into SimpleFacilityManager.displayFacilityInventory does not exist: "
							+ facility);
		}
		return ListOfFacilities.get(facility).displayInventory();
	}

	@Override
	public String displayFacilitySchedule(String facility) throws InvalidParamException, FacilityDoesNotExistException {
		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleFacilityManager.displayFacilitySchedule(String)");
		}
		if (ListOfFacilities.containsKey(facility) == false) {
			throw new FacilityDoesNotExistException(
					"String (facility) passed into SimpleFacilityManager.displayFacilitySchedule does not exist: "
							+ facility);
		}
		return ListOfFacilities.get(facility).displaySchedule();
	}

	@Override
	public boolean facilityHasItem(String facility, String item)
			throws InvalidParamException, FacilityDoesNotExistException {
		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleFacilityManager.facilityHasItem(String)");
		}
		if (item == null) {
			throw new InvalidParamException(
					"Null string(item) passed into SimpleFacilityManager.facilityHasItem(String)");
		}
		if (ListOfFacilities.containsKey(facility) == false) {
			throw new FacilityDoesNotExistException(
					"String (facility) passed into SimpleFacilityManager.facilityHasItem does not exist: " + facility);
		}
		return ListOfFacilities.get(facility).hasItem(item);
	}

	@Override
	public int getFacilityItemCount(String facility, String item)
			throws InvalidParamException, FacilityDoesNotExistException {
		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleFacilityManager.getFacilityName(String)");
		}
		if (item == null) {
			throw new InvalidParamException(
					"Null string(item) passed into SimpleFacilityManager.getFacilityItemCount(String)");
		}
		if (ListOfFacilities.containsKey(facility) == false) {
			throw new FacilityDoesNotExistException(
					"String (facility) passed into SimpleFacilityManager.getFacilityItemCount does not exist: "
							+ facility);
		}
		return ListOfFacilities.get(facility).getItemCount(item);
	}

	@Override
	public void updateFacilityItemCount(String facility, String item, int count)
			throws InvalidParamException, FacilityDoesNotExistException {
		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleFacilityManager.updateFacilityItemCount(String)");
		}
		if (item == null) {
			throw new InvalidParamException(
					"Null string(item) passed into SimpleFacilityManager.updateFacilityItemCount(String)");
		}
		if (count < 0) {
			throw new InvalidParamException(
					" Negative int(count) passed into SimpleFacilityManager.updateFacilityItemCount(int)");
		}
		if (ListOfFacilities.containsKey(facility) == false) {
			throw new FacilityDoesNotExistException(
					"String (facility) passed into SimpleFacilityManager.updateFacilityItemCount does not exist: "
							+ facility);
		}

		ListOfFacilities.get(facility).updateItemCount(item, count);
	}

	@Override
	public void reduceFacilityItemCount(String facility, String item, int count)
			throws InvalidParamException, FacilityDoesNotExistException {
		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleFacilityManager.reduceFacilityItemCount(String)");
		}
		if (item == null) {
			throw new InvalidParamException(
					"Null string(item) passed into SimpleFacilityManager.reduceFacilityItemCount(String)");
		}
		if (count < 0) {
			throw new InvalidParamException(
					" Negative int(count) passed into SimpleFacilityManager.reduceFacilityItemCount(int)");
		}
		if (ListOfFacilities.containsKey(facility) == false) {
			throw new FacilityDoesNotExistException(
					"String (facility) passed into SimpleFacilityManager.reduceFacilityItemCount does not exist: "
							+ facility);
		}
		ListOfFacilities.get(facility).reduceItemCount(item, count);
	}

	@Override
	public void increaseFacilityItemCount(String facility, String item, int count)
			throws InvalidParamException, FacilityDoesNotExistException {

		if (facility == null) {
			throw new InvalidParamException(
					"Null string(facility) passed into SimpleFacilityManager.increaseFacilityItemCount(String)");
		}
		if (item == null) {
			throw new InvalidParamException(
					"Null string(item) passed into SimpleFacilityManager.increaseFacilityItemCount(String)");
		}
		if (count < 0) {
			throw new InvalidParamException(
					" Negative int(count) passed into SimpleFacilityManager.increaseFacilityItemCount(int)");
		}
		if (ListOfFacilities.containsKey(facility) == false) {
			throw new FacilityDoesNotExistException(
					"String (facility) passed into SimpleFacilityManager.increaseFacilityItemCount does not exist: "
							+ facility);
		}
		ListOfFacilities.get(facility).increaseItemCount(item, count);
	}
}
