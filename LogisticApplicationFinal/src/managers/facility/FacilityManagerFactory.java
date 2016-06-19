package managers.facility;

import exceptions.InvalidParamException;
import exceptions.facility.FacilityMangerTypeDoesNotExist;
import exceptions.order.OrderMangerTypeDoesNotExist;

public class FacilityManagerFactory {

	public FacilityManager getFacilityManager(String FacilityManagerType)
			throws InvalidParamException, OrderMangerTypeDoesNotExist, FacilityMangerTypeDoesNotExist {
		if (FacilityManagerType == null) {
			throw new InvalidParamException(
					"Null String(FacilityManagerType) passed into FacilityManagerFactory.getFacilityManager(String)");
		} else if (FacilityManagerType.equalsIgnoreCase("Simple")) {
			return SimpleFacilityManager.getInstance();
		}
		throw new FacilityMangerTypeDoesNotExist(
				"No implementation exists for String(FacilityManagerType) in  FacilityManagerFactory.getFacilityManager(String):"
						+ FacilityManagerType);
	}

}
