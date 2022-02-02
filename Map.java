import java.util.ArrayList;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.HashMap;
public class Map {
	HashMap<Integer,ArrayList<Pair>> citiesBeforeBridge = new HashMap<Integer,ArrayList<Pair>>(); 
	HashMap<Integer,PriorityQueue<Edge>> citiesAfterBridge = new HashMap<Integer,PriorityQueue<Edge>>();
	int noCitiesBeforeBridge;
	int noCitiesAfterBridge;
	boolean pathStatus = true;
	int mecnunNode;
	int LeylaNode;
	ArrayList <String> finalPath = new ArrayList<String>(); 
	int finalDistance;
	int finalFee;
	boolean honeymoonStatus = true; 
	Pair nullPair = new Pair (-1, -1);
    //Constructor 
	public Map (int noCities , int totalNumber, int mecnunID) {
		this.noCitiesBeforeBridge = noCities;
		this.noCitiesAfterBridge = totalNumber - noCities + 1;
		this.mecnunNode = mecnunID -1 ;
		this.LeylaNode = noCitiesBeforeBridge - 1;
		
		
		for (int i = 0; i < this.noCitiesBeforeBridge; i ++) {
			citiesBeforeBridge.put(i, new ArrayList<Pair>());
		}
		for (int i = 0; i < this.noCitiesAfterBridge; i ++) {
			citiesAfterBridge.put(i, new PriorityQueue<Edge>());
		}
	}
	//Adding connections for the cities found before the river separating the country
	public void addConnectionsBeforeBridge(int ID1, int ID2, int weight) {
		citiesBeforeBridge.get(ID1).add(new Pair(ID2,weight));
	}
	
	
	//Adding connections for the cities found after the river separating the country
	public void addConnectionsAfterBridge(int ID1, int ID2, int weight) {
		citiesAfterBridge.get(ID1).add(new Edge(ID1,ID2,weight));
		citiesAfterBridge.get(ID2).add(new Edge(ID2,ID1,weight));


	}
	
	
	
	public ArrayList<Pair> getNeighborsBeforeBridge(int ID) {
		 return this.citiesBeforeBridge.get(ID);
	}
	

	//Implementation of Dijkstra's Algorithm
	
	public void findMinPath() {
		int distances[] = new int [noCitiesBeforeBridge];
		int parents[] = new int [noCitiesBeforeBridge];
		int visited[] = new int [noCitiesBeforeBridge];
		for (int counter = 0 ; counter <this.noCitiesBeforeBridge;counter ++) {
				visited[ counter ]  = 0;
		}
		distances[0] = 0;
		for (int counter = 1; counter <this.noCitiesBeforeBridge;counter++) {
			distances[counter] = Integer.MAX_VALUE;
			
		}
		parents[this.LeylaNode] = -1;
		PriorityQueue<Pair> path = new PriorityQueue<Pair>();
		path.add(new Pair(this.mecnunNode,0));
		while (path.size() >0){
			int vertex = path.poll().ID;
			visited[vertex] = 1;
			ArrayList<Pair> neighbors = this.getNeighborsBeforeBridge(vertex);
			for (Pair neighbor : neighbors) {
				if (visited[neighbor.ID] == 0) {
					if (neighbor.weight + distances[vertex] <= distances[neighbor.ID]) {
						distances[neighbor.ID] = neighbor.weight + distances[vertex];
						parents[neighbor.ID] = vertex;
						neighbor.weight = distances[neighbor.ID];
						path.add(neighbor);
					}
				}
				}
		}
		Stack<Integer> path_2 = new Stack<Integer>();
		path_2.add(this.LeylaNode);
		if (parents[this.LeylaNode] != -1) {
		while (path_2.peek() != this.mecnunNode){
			int vertexParent = path_2.peek();
			path_2.add(parents[vertexParent]);
				
		}
		}
		else {
			this.pathStatus = false;
		}
		if (pathStatus == true) {
			this.finalDistance = distances[LeylaNode];
			while (path_2.size()>0) {
				this.finalPath.add("c".concat(String.valueOf(path_2.pop()+1)));
			}
		}
		else {
			this.finalDistance = Integer.MAX_VALUE;
		}
	}
	//Implementation of Prim's algorithm
	public void honneymoonFees() {
		if (pathStatus = true) {
		int summation = 0;
		int [] isVisited = new int [this.noCitiesAfterBridge];
		for (int i = 0; i<this.noCitiesAfterBridge;i++) {
			isVisited[i] = 0;
		}
		PriorityQueue<Edge> path = new PriorityQueue<Edge>();
		while (citiesAfterBridge.get(0).size()>0) {
			path.add(citiesAfterBridge.get(0).poll());
		}
		while (path.size()>0) {
			int ID1 = path.peek().ID1;
			int ID2 = path.peek().ID2;	
			summation += path.poll().weight;
			isVisited[ID1] = 1;
			isVisited[ID2] = 1;
			while (citiesAfterBridge.get(ID2).size()>0) {
				path.add(citiesAfterBridge.get(ID2).poll());
			}
			int ID;
			while (path.size()>0) {
				ID = path.peek().ID2;
				if (isVisited[ID] == 0) {
					break;
				}
				else {
					path.poll();
				}
			}
		}
		
		for (int i = 0 ; i<this.noCitiesAfterBridge; i++) {
			if (isVisited[i] == 0) {
			   this.honeymoonStatus = false;
			   break;
			}

		}
		this.finalFee = 2 * summation;	
	}
		else {
			this.honeymoonStatus = false;
		}
	}



	public HashMap<Integer, PriorityQueue<Edge>> getCitiesAfterBridge() {
		return citiesAfterBridge;
	}

	public int getNoCitiesBeforeBridge() {
		return noCitiesBeforeBridge;
	}

	public int getNoCitiesAfterBridge() {
		return noCitiesAfterBridge;
	}

	public boolean isPathStatus() {
		return pathStatus;
	}

	public int getMecnunNode() {
		return mecnunNode;
	}

	public int getLeylaNode() {
		return LeylaNode;
	}

	public ArrayList<String> getFinalPath() {
		return finalPath;
	}

	public int getFinalDistance() {
		return finalDistance;
	}

	public int getFinalFee() {
		return finalFee;
	}

	public boolean isHoneymoonStatus() {
		return honeymoonStatus;
	}
}
