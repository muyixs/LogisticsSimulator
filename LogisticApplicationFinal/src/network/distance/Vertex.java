package network.distance;

import exceptions.InvalidParamException;

public class Vertex {
	final private String id;
	final private String name;

	public Vertex(String id, String name) throws InvalidParamException {
		if (id == null) {
			throw new InvalidParamException("Null string(id) passed into Vertex(String,String)");
		}
		if (name == null) {
			throw new InvalidParamException("Null string(vertex) passed into Vertex(String,String)");
		}
		if (id.equals(name) == false) {
			throw new InvalidParamException("Different id(String) and name(String) passed into Vertex(String,String)");
		}
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
}
