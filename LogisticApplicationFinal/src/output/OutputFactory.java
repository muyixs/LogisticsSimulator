package output;

import exceptions.InvalidParamException;
import exceptions.OutputTypeDoesNotExist;

public class OutputFactory {
	public Output getOutput(String OutputType) throws InvalidParamException, OutputTypeDoesNotExist {
		if (OutputType == null) {
			throw new InvalidParamException("Null String(OutputType) passed into OutputFactory.getOutput(String)");
		} else if (OutputType.equalsIgnoreCase("Facility")) {
			return new SimpleFacilityOutput();
		} else if (OutputType.equalsIgnoreCase("Order")) {
			return new SimpleOrderOutput();
		}
		throw new OutputTypeDoesNotExist(
				"No implementation exists for String(outputType) in OutputFactory.getOutput(String):" + OutputType);

	}

}
