package loaders.network;

import exceptions.InvalidParamException;
import loaders.Loader;
import network.distance.Graph;

public class NetworkLoader implements NetworkLoaderInterface, Loader {
	private NetworkLoadFileType loadbyFileType;
	private Graph facilityNetwork;

	private void setFileTypeLoader(String fileName) throws InvalidParamException {
		String extension = "";
		if (fileName == null) {
			throw new InvalidParamException(
					"Null string(fileName) passed into  NetworkLoader.setFileTypeLoader(String)");
		}
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}
		if (extension.equalsIgnoreCase("xml")) {
			this.loadbyFileType = new NetworkLoadXMLImpl();
		} else {
			this.loadbyFileType = new NetworkLoadNullImpl();
		}
	}

	@Override
	public void load(String fileName) throws InvalidParamException {
		setFileTypeLoader(fileName);
		setFacilityNetwork(loadbyFileType.loadFile(fileName));
	}

	@Override
	public void load(String file, String inventory) {

	}

	@Override
	public Graph getFacilityNetwork() {
		return facilityNetwork;
	}

	public void setFacilityNetwork(Graph facilityNetwork) throws InvalidParamException {
		if (facilityNetwork == null) {
			throw new InvalidParamException(
					"Null Graph(facilityNetwork) passed into NetworkLoader.setFacilityNetwork(Graph)");
		}
		this.facilityNetwork = facilityNetwork;
	}

}
