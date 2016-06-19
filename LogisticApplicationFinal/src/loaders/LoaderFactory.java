package loaders;

import exceptions.InvalidParamException;
import exceptions.LoaderTypeDoesNotExist;
import loaders.facility.FacilityLoader;
import loaders.item.ItemLoader;
import loaders.network.NetworkLoader;
import loaders.order.OrderLoader;

public class LoaderFactory {

	public Loader getLoader(String loaderType) throws InvalidParamException, LoaderTypeDoesNotExist {
		if (loaderType == null) {
			throw new InvalidParamException("Null string(loaderType) passed into LoaderFactory.getLoader(String)");
		} else if (loaderType.equalsIgnoreCase("Item")) {
			return new ItemLoader();
		} else if (loaderType.equalsIgnoreCase("Facility")) {
			return new FacilityLoader();
		} else if (loaderType.equalsIgnoreCase("Network")) {
			return new NetworkLoader();
		} else if (loaderType.equalsIgnoreCase("Order")) {
			return new OrderLoader();
		}
		throw new LoaderTypeDoesNotExist(
				"Implementation for String(loaderType) passed into LoaderFactory.getLoader(String) does not exist: "
						+ loaderType);
	}
}
