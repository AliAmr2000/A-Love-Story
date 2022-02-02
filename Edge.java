
public class Edge implements Comparable<Edge>{
	int ID1;
	int ID2;
	int weight;
	
	public Edge(int ID1, int ID2, int weight) {
		this.ID1 = ID1;
		this.ID2 = ID2;
		this.weight = weight;
	}
	// CompareTo method for comparing objects of type Edge in the process of finding the minimum spanning tree
	public int compareTo(Edge other) {
		return this.weight - other.weight;
	}

}
