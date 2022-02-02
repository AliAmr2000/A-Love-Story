
public class Pair implements Comparable<Pair>{
	int ID;
	int weight;
	public Pair(int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
	}
	//compareTo method for sorting Pair objects while figuring out the minimum path to Leyla's node
	public int compareTo(Pair other) {
		return this.weight - other.weight;
	}


	

}
