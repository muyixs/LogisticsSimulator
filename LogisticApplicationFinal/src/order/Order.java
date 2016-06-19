package order;

import java.util.Map;

import exceptions.InvalidParamException;

public interface Order {
	String getId();

	void setId(String id) throws InvalidParamException;

	int getTime();

	void setTime(int time) throws InvalidParamException;

	String getDestination();

	void setDestination(String destination) throws InvalidParamException;

	String getPriority();

	void setPriority(String priority) throws InvalidParamException;

	Map<String, Integer> getListOfItems();

	void addItem(String item, int quantity) throws InvalidParamException;

	String displayOrder();
}
