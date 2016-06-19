package facility.inventory;

import java.util.HashMap;
import java.util.Map;

import exceptions.InvalidParamException;

public class SimpleInventory implements Inventory {
	private final Map<String, Integer> facilityInventory = new HashMap<String, Integer>();

	@Override
	public void addItem(String item, int count) throws InvalidParamException {
		if (item == null) {
			throw new InvalidParamException("Null string(item) passed into SimpleInventory.addItem(int)");
		}
		if (count < 1) {
			throw new InvalidParamException(
					(count == 0 ? "Zero" : "Negative") + " int(count) passed into  SimpleInventory.addItem(int)");
		}

		if (facilityInventory.containsKey(item)) {
			System.out.println("Item already exists in inventory");
		} else {
			facilityInventory.put(item, count);
		}
	}

	@Override
	public boolean hasItem(String item) throws InvalidParamException {
		if (item == null) {
			throw new InvalidParamException("Null string(item) passed into SimpleInventory.hasItem(string)");
		}
		if (facilityInventory.containsKey(item) && facilityInventory.get(item) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int getItemCount(String item, int limit) throws InvalidParamException {
		if (item == null) {
			throw new InvalidParamException("Null string(item) passed into SimpleInventory.getItemCount(String, int)");
		}
		if (limit < 1) {
			throw new InvalidParamException((limit == 0 ? "Zero" : "Negative")
					+ " int(limit) passed into  SimpleInventory.getItemCount(String,int)");
		}
		if (facilityInventory.containsKey(item)) {
			if (facilityInventory.get(item) <= limit) {
				return facilityInventory.get(item);
			} else {
				return limit;
			}
		}
		return 0;
	}

	@Override
	public int getItemCountForRecord(String item, int quantity, int limit) throws InvalidParamException {
		if (item == null) {
			throw new InvalidParamException(
					"Null string(item) passed into SimpleInventory.getItemCountForRecord(String, int,int)");
		}
		if (limit < 1) {
			throw new InvalidParamException((limit == 0 ? "Zero" : "Negative")
					+ " int(limit) passed into  SimpleInventory.getItemCountForRecord(String,int,int)");
		}
		if (quantity < 1) {
			throw new InvalidParamException((limit == 0 ? "Zero" : "Negative")
					+ " int(quantity) passed into  SimpleInventory.getItemCountForRecord(String,int,int)");
		}
		if (facilityInventory.containsKey(item)) {
			if (facilityInventory.get(item) < quantity) {
				if (facilityInventory.get(item) <= limit) {
					return facilityInventory.get(item);
				} else {
					return limit;
				}
			} else {
				if (quantity <= limit) {
					return quantity;
				} else {
					return limit;
				}
			}
		} else {
			return 0;
		}
	}

	@Override
	public void updateItemCount(String item, int count) throws InvalidParamException {
		if (item == null) {
			throw new InvalidParamException(
					"Null string(item) passed into SimpleInventory.updateItemCount(String,int)");
		}
		if (count < 0) {
			throw new InvalidParamException((count == 0 ? "Negative" : "Negative")
					+ " int(count) passed into  SimpleInventory.updateItemCount(String,int)");
		}
		if (facilityInventory.containsKey(item)) {
			facilityInventory.replace(item, facilityInventory.get(item), count);
		}
	}

	@Override
	public void reduceItemCount(String item, int count) throws InvalidParamException {
		if (item == null) {
			throw new InvalidParamException("Null string(item) passed into SimpleInventory.reduceItemCount(int)");
		}
		if (count < 0) {
			throw new InvalidParamException((count == 0 ? "Negative" : "Negative")
					+ " int(count) passed into  SimpleInventory.reduceItemCount(int)");
		}
		if (facilityInventory.containsKey(item)) {
			int itemOldCount = facilityInventory.get(item);
			int itemNewCount = itemOldCount - count;
			facilityInventory.replace(item, itemOldCount, itemNewCount);
		}
	}

	@Override
	public void increaseItemCount(String item, int count) throws InvalidParamException {
		if (item == null) {
			throw new InvalidParamException("Null string(item) passed into SimpleInventory.increaseItemCount(int)");
		}
		if (count < 0) {
			throw new InvalidParamException((count == 0 ? "Negative" : "Negative")
					+ " int(count) passed into  SimpleInventory.increaseItemCount(int)");
		}
		if (facilityInventory.containsKey(item)) {
			int itemOldCount = facilityInventory.get(item);
			int itemNewCount = itemOldCount + count;
			facilityInventory.replace(item, itemOldCount, itemNewCount);
		}
	}

	@Override
	public String displayDepletedItems() {
		StringBuilder sb = new StringBuilder();
		for (String item : facilityInventory.keySet()) {
			if (facilityInventory.get(item) == 0) {
				sb.append(String.format("     Item ID: %s\n", item));
			}
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String item : facilityInventory.keySet()) {
			sb.append(String.format("    Item ID: %s Quantity %d \n", item, facilityInventory.get(item)));
		}
		return sb.toString();
	}

}
