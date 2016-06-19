package network;

import java.util.List;

import exceptions.InvalidParamException;
import exceptions.facility.FacilityDoesNotExistException;
import loaders.network.NetworkLoaderInterface;

public interface NetworkProcessor {
	double cost(String source, String destination) throws InvalidParamException, FacilityDoesNotExistException;

	List<String> getLinks(String facility) throws InvalidParamException, FacilityDoesNotExistException;

	List<String> getFacilitiesList();

	void setFacilityNetwork(NetworkLoaderInterface networkLoader) throws InvalidParamException;

	void setHoursPerDay(int hours) throws InvalidParamException;

	void setmilesPerHour(int miles) throws InvalidParamException;

}
