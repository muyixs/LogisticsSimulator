package item;

import exceptions.InvalidParamException;
import exceptions.item.ItemTypeDoesNotExistException;

public class ItemFactory {

	public Item getItem(String ItemType) throws InvalidParamException, ItemTypeDoesNotExistException {
		if (ItemType == null) {
			throw new InvalidParamException("Null String(ItemType) passed into ItemFactory.getItem(String)");
		} else if (ItemType.equalsIgnoreCase("Simple")) {
			return new SimpleItem();
		}
		throw new ItemTypeDoesNotExistException(
				"No implementation exists for String(ItemType) in ItemFactory.getItem(String):" + ItemType);
	}
}
