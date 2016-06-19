package loaders.facility;
import java.util.Map;

import facility.Facility;

public interface FacilityLoadFileType {
	Map<String, Facility> loadFile(String facilityFile, String inventoryFile);
}
