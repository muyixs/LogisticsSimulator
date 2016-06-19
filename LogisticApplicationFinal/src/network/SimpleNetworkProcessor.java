package network;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidParamException;
import exceptions.facility.FacilityDoesNotExistException;
import loaders.network.NetworkLoaderInterface;
import network.distance.DijkstraAlgorithm;
import network.distance.Graph;
import network.distance.Vertex;

public class SimpleNetworkProcessor implements NetworkProcessor {

	private Graph facilityNetwork;
	private double hoursPerDay;
	private double milesPerHour;
	private static SimpleNetworkProcessor ourInstance;

	public static SimpleNetworkProcessor getInstance() {
		if (ourInstance == null) {
			ourInstance = new SimpleNetworkProcessor();
		}
		return ourInstance;
	}

	private SimpleNetworkProcessor() {
	}

	@Override
	public double cost(String source, String destination) throws InvalidParamException, FacilityDoesNotExistException {
		if (source == null) {
			throw new InvalidParamException("Null string(source) passed into Network.cost(String,String)");
		}
		if (destination == null) {
			throw new InvalidParamException("Null string(destination) passed into Network.cost(String,String)");
		}
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(facilityNetwork);
		Vertex currentFacility = dijkstra.getFacility(source);
		Vertex destinationFacility = dijkstra.getFacility(destination);
		dijkstra.execute(currentFacility);
		double distance = dijkstra.distance(destinationFacility);
		double travelTime = distance / (hoursPerDay * milesPerHour);
		return travelTime;
	}

	// private double RoundTo2Decimals(double val) {
	// DecimalFormat df2 = new DecimalFormat("###.##");
	// System.out.println(Math.round(val));
	// return Double.valueOf(df2.format(val));
	// }

	@Override
	public List<String> getLinks(String facility) throws InvalidParamException, FacilityDoesNotExistException {
		if (facility == null) {
			throw new InvalidParamException("Null string(facility) passed into Network.getLinks(String)");
		}
		List<String> connectedFacilities = new ArrayList<String>();
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(facilityNetwork);
		List<Vertex> links = dijkstra.getLinkedVertex(dijkstra.getFacility(facility));
		for (Vertex link : links) {
			connectedFacilities.add(link.getName());
		}
		return connectedFacilities;
	}

	@Override
	public List<String> getFacilitiesList() {
		List<String> facilities = new ArrayList<String>();
		for (Vertex facility : facilityNetwork.getVertexes()) {
			facilities.add(facility.getName());
		}
		return facilities;
	}

	@Override
	public void setFacilityNetwork(NetworkLoaderInterface networkLoader) throws InvalidParamException {
		if (networkLoader == null) {
			throw new InvalidParamException(
					"Null networkLoader(facility) passed into Network.setFacilityNetwork(NetworkLoaderInterface)");
		}
		this.facilityNetwork = networkLoader.getFacilityNetwork();
	}

	@Override
	public void setHoursPerDay(int hours) throws InvalidParamException {
		if (hours < 0) {
			throw new InvalidParamException(
					"Invalid HoursPerDay value passed into Network.setHoursPerDay(int): " + hours);
		}
		hoursPerDay = hours;
	}

	@Override
	public void setmilesPerHour(int miles) throws InvalidParamException {
		if (miles < 0) {
			throw new InvalidParamException(
					"Invalid MilesPerHour value passed into Network.setMilesPerHour(int): " + miles);
		}
		milesPerHour = miles;
	}

	public double getMilesPerHour() {
		return milesPerHour;
	}

	public double getHoursPerDay() {
		return hoursPerDay;
	}

}
