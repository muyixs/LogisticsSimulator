package facility;

import exceptions.InvalidParamException;
import exceptions.facility.FacilityTypeDoesNotExistException;

public class FacilityFactory {

	public Facility getFacility(String FacilityType) throws InvalidParamException, FacilityTypeDoesNotExistException {
		if (FacilityType == null) {
			if (FacilityType == null) {
				throw new InvalidParamException(
						"Null String(facilityType) passed into FacilityFactory.getFacility(String)");
			}
		} else if (FacilityType.equalsIgnoreCase("Simple")) {
			return new SimpleFacility();
		}
		throw new FacilityTypeDoesNotExistException(
				"No implementation exists for String(facilityType) in FacilityFactory.getFacility(String):"
						+ FacilityType);
	}

}
