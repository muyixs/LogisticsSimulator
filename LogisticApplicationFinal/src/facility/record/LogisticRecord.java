package facility.record;

import exceptions.InvalidParamException;

public interface LogisticRecord {
	String getItem();

	void setTravelEnd(int travelEnd) throws InvalidParamException;

	int getTravelEnd();

	void setTravelStart(int travelStart) throws InvalidParamException;

	int getTravelStart();

	void setProcessingEnd(int processingEnd) throws InvalidParamException;

	int getProcessingEnd();

	void setProcessingStart(int processingStart) throws InvalidParamException;

	int getProcessingStart();

	void setQuantity(int quantity) throws InvalidParamException;

	int getQuantity();

	void setFacilityName(String facility) throws InvalidParamException;

	String getFacilityName();

	void setItem(String item) throws InvalidParamException;

	void setTotalCost(double TotalCost) throws InvalidParamException;

	double getTotalCost();

	void setOrderQuantity(int orderQuantity) throws InvalidParamException;

	double getPercentageOfQuantity();

	int getOrderQuantity();

	void setPercentageOfQuantity(int quantity, int orderQuantity) throws InvalidParamException;

}
