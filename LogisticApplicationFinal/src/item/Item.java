package item;

import exceptions.InvalidParamException;

public interface Item {
	String getId();

	void setId(String id) throws InvalidParamException;

	int getCost();

	void setCost(int cost) throws InvalidParamException;
}
