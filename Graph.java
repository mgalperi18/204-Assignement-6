import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road>{

	Hashtable<Integer, Road> road = new Hashtable<Integer, Road>();
	Hashtable<Integer, Town> town = new Hashtable<Integer, Town>();
	
	public int hashIndex(Town townSource, Town townDestination) {
		return townSource.hashCode() * townDestination.hashCode();
	}
	
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		// TODO Auto-generated method stub
		//return null;
		return road.get(hashIndex(sourceVertex, destinationVertex));
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		// TODO Auto-generated method stub
		Road r = new Road(sourceVertex, destinationVertex, weight, description);
		
		if(containsVertex(sourceVertex) && containsVertex(destinationVertex)) {
			road.put(hashIndex(sourceVertex, destinationVertex), r);
			
			sourceVertex.roads.add(r);
			destinationVertex.roads.add(r);
			
			sourceVertex.towns.add(destinationVertex);
			destinationVertex.towns.add(sourceVertex);
			
			return r;
		}
		else
			throw new IllegalArgumentException();

	}

	@Override
	public boolean addVertex(Town v) {
		// TODO Auto-generated method stub
		if(!containsVertex(v)) {
			town.put(v.hashCode(), v);
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		// TODO Auto-generated method stub
		if(road.containsKey(hashIndex(sourceVertex, destinationVertex)))
			return true;
		else
			return false;
	}

	@Override
	public boolean containsVertex(Town v) {
		// TODO Auto-generated method stub
		if(town.containsKey(v.hashCode()))
			return true;
		else
			return false;
	}

	@Override
	public Set<Road> edgeSet() {
		// TODO Auto-generated method stub
		//return null;
		Set<Road> s = new HashSet<Road>(road.values());
		return s;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		// TODO Auto-generated method stub
		//return null;
		Set<Road> s = new HashSet<Road>(vertex.roads);
		return s;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		// TODO Auto-generated method stub
		//return null;
		return road.remove(hashIndex(sourceVertex, destinationVertex));
	}

	@Override
	public boolean removeVertex(Town v) {
		// TODO Auto-generated method stub
		return town.remove(v.hashCode(), v);
		//return false;
	}

	@Override
	public Set<Town> vertexSet() {
		// TODO Auto-generated method stub
		//return null;
		Set<Town> s = new HashSet<Town>(town.values());
		return s;
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		// TODO Auto-generated method stub
		Comparator<Town> c = new Town("Hello");
		PriorityQueue<Town> pQ = new PriorityQueue<Town>();
		
		Town smallesTown = null;
		sourceVertex.weight = 0;
		sourceVertex.visited = true;
		
		pQ.add(smallesTown);
		
		while(!pQ.isEmpty()) {
			smallesTown = pQ.peek();
			pQ.remove();
			
			for(Town n: smallesTown.towns) {
				if(!n.visited) {
					int i = smallesTown.weight + getEdge(smallesTown, n).distance;
					
					if(i < n.weight) {
						n.weight = i;
						n.previous = smallesTown;
				
						pQ.add(n);
					}
				}
			}
			
			smallesTown.visited = true;
		}
	}

}
