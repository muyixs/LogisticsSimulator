package loaders.network;

import network.distance.Graph;

public class NetworkLoadNullImpl implements NetworkLoadFileType {
	@Override
	public Graph loadFile(String File) {
		Graph graph = new Graph();
		System.out.println("Implementation to open this file format does not yet exist.");
		return graph;
	}
}
