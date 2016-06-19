package managers.item;

import exceptions.InvalidParamException;
import exceptions.item.ItemDoesNotExist;
import loaders.item.ItemLoaderInterface;

public interface ItemManager {
	void setListOfItems(ItemLoaderInterface itemLoader) throws InvalidParamException;

	int getCost(String id) throws InvalidParamException, ItemDoesNotExist;

	boolean itemExists(String id) throws InvalidParamException;

	String displayItems();
}
