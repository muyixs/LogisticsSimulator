package loaders.network;

import exceptions.InvalidParamException;
import network.distance.Graph;

public interface NetworkLoadFileType {
	public Graph loadFile(String File) throws InvalidParamException;
}
