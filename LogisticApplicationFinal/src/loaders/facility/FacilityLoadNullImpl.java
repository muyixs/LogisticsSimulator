package loaders.facility;
import java.util.HashMap;
import java.util.Map;

import facility.Facility;

public class FacilityLoadNullImpl implements FacilityLoadFileType {
	@Override
	public Map<String, Facility> loadFile(String facilityFile, String inventoryFile) {
		Map<String, Facility> facilities = new HashMap<String, Facility>();
		System.out.println("Implementation to open this file format does not yet exist.");
		return facilities;
	}
}
