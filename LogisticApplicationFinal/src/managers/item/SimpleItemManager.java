package managers.item;

import java.util.Map;

import exceptions.InvalidParamException;
import exceptions.item.ItemDoesNotExist;
import item.Item;
import loaders.item.ItemLoaderInterface;

public class SimpleItemManager implements ItemManager {

	private Map<String, Item> ListOfItems;

	private static SimpleItemManager ourInstance;

	public static SimpleItemManager getInstance() {
		if (ourInstance == null) {
			ourInstance = new SimpleItemManager();
		}
		return ourInstance;
	}

	private SimpleItemManager() {
	}

	@Override
	public void setListOfItems(ItemLoaderInterface itemLoader) throws InvalidParamException {
		if (itemLoader == null) {
			throw new InvalidParamException(
					"Null ItemLoaderInterface(itemLoader) passed into SimpleItemManager.setListOfItems(ItemLoaderInterface)");
		}
		this.ListOfItems = itemLoader.getItems();
	}

	@Override
	public int getCost(String id) throws InvalidParamException, ItemDoesNotExist {
		if (id == null) {
			throw new InvalidParamException("Null string(id) passed into SimpleItemManager.getCost(String)");
		}
		if (ListOfItems.containsKey(id) == false) {
			throw new ItemDoesNotExist("Item passed into SimpleItemManger.getCost(String) does not exist: " + id);
		}
		return ListOfItems.get(id).getCost();
	}

	@Override
	public boolean itemExists(String id) throws InvalidParamException {
		if (id == null) {
			throw new InvalidParamException("Null string(id) passed into SimpleItemManager.itemExists(String)");
		}
		return ListOfItems.containsKey(id);
	}

	@Override
	public String displayItems() {
		StringBuilder sb = new StringBuilder();
		for (String item : ListOfItems.keySet()) {
			sb.append(String.format("ItemID: %s  Cost:%d \n", item, ListOfItems.get(item).getCost()));
		}
		return sb.toString();
	}

}
