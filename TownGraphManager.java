import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface {

	Graph g = new Graph();
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		// TODO Auto-generated method stub
		//return false;
		g.addVertex(getTown(town1));
		g.addVertex(getTown(town2));
		g.addEdge(getTown(town1), getTown(town2), weight, roadName);
		return true;
	}

	@Override
	public String getRoad(String town1, String town2) {
		// TODO Auto-generated method stub
		//return null;
		return g.getEdge(getTown(town1), getTown(town2)).getName();
	}

	@Override
	public boolean addTown(String v) {
		// TODO Auto-generated method stub
		//return false;
		g.addVertex(getTown(v));
		return true;
	}

	@Override
	public Town getTown(String name) {
		// TODO Auto-generated method stub
		Town t = new Town(name);
		return t;
	}

	@Override
	public boolean containsTown(String v) {
		// TODO Auto-generated method stub
		//return false;
		if(g.containsVertex(getTown(v)))
			return true;
		else 
			return false;
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		// TODO Auto-generated method stub
		//return false;
		if(g.containsEdge(getTown(town1), getTown(town2)))
			return true;
		else 
			return false;
	}

	@Override
	public ArrayList<String> allRoads() {
		// TODO Auto-generated method stub
		//return null;
		ArrayList<String> allRoads = new ArrayList<String>();
		
		for(Road r: g.edgeSet() ) {
			allRoads.add(r.getName());
		}
		
		Collections.sort(allRoads);		
		return allRoads;
		
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		// TODO Auto-generated method stub
		//return false;
		g.removeEdge(getTown(town1), getTown(town2), 1, road);
		return containsRoadConnection(town1, town2);
	}

	@Override
	public boolean deleteTown(String v) {
		// TODO Auto-generated method stub
		//return false;
		g.removeVertex(getTown(v));
		return containsTown(v);
	}

	@Override
	public ArrayList<String> allTowns() {
		// TODO Auto-generated method stub
		//return null;
		ArrayList<String> allTown = new ArrayList<String>();
		
		for(Town t: g.vertexSet() ) {
			allTown.add(t.getName());
		}
		
		Collections.sort(allTown);		
		return allTown;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		// TODO Auto-generated method stub
		//return null;
		ArrayList<String> s = new ArrayList<String>();
		s = g.shortestPath(getTown(town1), getTown(town2));
		return s;
	}

	public void populateTownGraph(File selectedFile) throws FileNotFoundException  {
		
		Scanner scannner = new Scanner(selectedFile);
	}
	
}
