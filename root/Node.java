package root;

public class Node {
	public int id;
	public String data;
	
	public Node(int id, String data) {
		this.id = id;
		this.data = data;
	}
	
	public int getId() {
		return this.id;
	}
	public String getData() {
		return this.data;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
    	if(!(obj instanceof Node))
    		return false;
		Node other = (Node) obj;
		if (data == null && other.data != null) return false;
		else if (!data.equals(other.data)) return false;
		else if (id != other.id) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + id;
		return result;
	}
	
	@Override
	public String toString() {
		return "{" + id + ", " + data + "}";
	}
}
