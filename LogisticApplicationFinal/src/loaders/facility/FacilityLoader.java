package loaders.facility;

import java.util.Map;

import exceptions.InvalidParamException;
import facility.Facility;
import loaders.Loader;

public class FacilityLoader implements Loader, FacilityLoaderInterface {
	private FacilityLoadFileType loadbyFileType;
	private Map<String, Facility> facilities;

	@Override
	public void load(String file) {

	}

	private void setFileTypeLoader(String file, String inventory) throws InvalidParamException {
		String fileExtension = "";
		String inventoryExtension = "";
		if (file == null) {
			throw new InvalidParamException("Null string(file) passed into FacilityLoader.setFileTypeLoader(String)");
		}
		if (inventory == null) {
			throw new InvalidParamException(
					"Null string(inventory) passed into FacilityLoader.setFileTypeLoader(String)");
		}
		int i = file.lastIndexOf('.');
		int x = inventory.lastIndexOf('.');
		if (i > 0 && x > 0) {
			fileExtension = file.substring(i + 1);
			inventoryExtension = inventory.substring(i + 1);
		}
		if (fileExtension.equalsIgnoreCase("xml") && inventoryExtension.equalsIgnoreCase("xml")) {
			this.loadbyFileType = new FacilityLoadXMLImpl();
		} else {
			this.loadbyFileType = new FacilityLoadNullImpl();
		}
	}

	@Override
	public void load(String file, String inventory) throws InvalidParamException {
		setFileTypeLoader(file, inventory);
		setFacilities(loadbyFileType.loadFile(file, inventory));
	}

	@Override
	public Map<String, Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(Map<String, Facility> facilities) throws InvalidParamException {
		if (facilities == null) {
			throw new InvalidParamException(
					"Null Map<String, Facility>(facilities) passed into Networkloader.setFacilities(Map<String, Facility>)");
		}
		this.facilities = facilities;
	}
}
