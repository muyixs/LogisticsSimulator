package facility.schedule;

import exceptions.InvalidParamException;

public interface Schedule {
	void setRate(int rate);

	void setDays(int days) throws InvalidParamException;

	void processOrder(int quantity, int start) throws InvalidParamException;

	public String displaySchedule();

	int getFirstAvailableDay();

	int processOrderforLogisticRecord(int quantity, int start) throws InvalidParamException;

	double numberofDaysToProcess(int quantity, int start) throws InvalidParamException;
}
