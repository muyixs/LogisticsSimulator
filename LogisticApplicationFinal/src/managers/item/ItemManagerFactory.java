package managers.item;

import exceptions.InvalidParamException;
import exceptions.item.ItemManagerTypeDoesNotExist;
import exceptions.order.OrderMangerTypeDoesNotExist;

public class ItemManagerFactory {
	public ItemManager getItemManager(String ItemManagerType)
			throws InvalidParamException, OrderMangerTypeDoesNotExist, ItemManagerTypeDoesNotExist {
		if (ItemManagerType == null) {
			throw new InvalidParamException(
					"Null String(ItemManagerType) passed into ItemManagerFactory.getItemManager(String)");
		} else if (ItemManagerType.equalsIgnoreCase("Simple")) {
			return SimpleItemManager.getInstance();
		}
		throw new ItemManagerTypeDoesNotExist(
				"No implementation exists for String(OrderManagerType) in OrderManagerFactory.getOrderManager(String):"
						+ ItemManagerType);
	}
}
