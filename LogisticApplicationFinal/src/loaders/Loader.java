package loaders;

import exceptions.InvalidParamException;

public interface Loader {
	void load(String file) throws InvalidParamException;

	void load(String file, String Inventory) throws InvalidParamException;

}
