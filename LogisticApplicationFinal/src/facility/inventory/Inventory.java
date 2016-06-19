package facility.inventory;

import exceptions.InvalidParamException;

public interface Inventory {
	boolean hasItem(String item) throws InvalidParamException;

	int getItemCount(String item, int limit) throws InvalidParamException;

	void updateItemCount(String item, int count) throws InvalidParamException;

	void reduceItemCount(String item, int count) throws InvalidParamException;

	void addItem(String item, int count) throws InvalidParamException;

	void increaseItemCount(String item, int count) throws InvalidParamException;

	String displayDepletedItems();

	int getItemCountForRecord(String item, int quantity, int limit) throws InvalidParamException;
}
