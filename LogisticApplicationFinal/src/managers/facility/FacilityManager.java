package managers.facility;

import java.util.List;
import java.util.Map;

import exceptions.InvalidParamException;
import exceptions.facility.FacilityDoesNotExistException;
import facility.record.LogisticRecord;
import loaders.facility.FacilityLoaderInterface;

public interface FacilityManager {
	void setListOfFacilities(FacilityLoaderInterface facilityLoader) throws InvalidParamException;

	String getFacilityName(String facility) throws InvalidParamException, FacilityDoesNotExistException;

	void displayFacilityNames();

	int getFacilityCost(String facility) throws InvalidParamException, FacilityDoesNotExistException;

	int getFacilityRate(String facility) throws InvalidParamException, FacilityDoesNotExistException;

	String displayFacilityInventory(String facility) throws InvalidParamException, FacilityDoesNotExistException;

	boolean facilityHasItem(String facility, String item) throws InvalidParamException, FacilityDoesNotExistException;

	int getFacilityItemCount(String facility, String item) throws InvalidParamException, FacilityDoesNotExistException;

	void updateFacilityItemCount(String facility, String item, int count)
			throws InvalidParamException, FacilityDoesNotExistException;

	void reduceFacilityItemCount(String facility, String item, int count)
			throws InvalidParamException, FacilityDoesNotExistException;

	void increaseFacilityItemCount(String facility, String item, int count)
			throws InvalidParamException, FacilityDoesNotExistException;

	List<String> facilitiesWithItem(String item) throws InvalidParamException, FacilityDoesNotExistException;

	String displayDepletedItems(String facility) throws InvalidParamException, FacilityDoesNotExistException;

	String displayFacilitySchedule(String facility)
			throws InvalidParamException, FacilityDoesNotExistException, FacilityDoesNotExistException;

	Map<String, LogisticRecord> getFacilityRecords(String item, int quantity, int start)
			throws InvalidParamException, FacilityDoesNotExistException;

	void updateSchedule(String facility, int quantity, int start)
			throws InvalidParamException, FacilityDoesNotExistException;

	double numOfDaystoProcess(String facility, int quantity, int start)
			throws InvalidParamException, FacilityDoesNotExistException;

	int getFacilityLimit(String facility) throws InvalidParamException, FacilityDoesNotExistException;

}
