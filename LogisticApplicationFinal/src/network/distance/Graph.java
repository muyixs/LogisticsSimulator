package network.distance;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidParamException;

public class Graph {
	private final List<Vertex> vertexes;
	private final List<Edge> edges;
	Vertex Facility;

	public Graph(List<Vertex> vertexes, List<Edge> edges) throws InvalidParamException {
		if (vertexes == null) {
			throw new InvalidParamException("Null List<Vertex>(vertexes) passed into Graph(Vertexes,Edges)");
		}
		if (edges == null) {
			throw new InvalidParamException("Null List<Edge>(edges) passed into Graph(Vertexes,Edges)");
		}
		this.vertexes = vertexes;
		this.edges = edges;
	}

	public Graph() {
		this.vertexes = new ArrayList<Vertex>();
		this.edges = new ArrayList<Edge>();
	}

	public List<Vertex> getVertexes() {
		return vertexes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

}
