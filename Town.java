import java.util.ArrayList;
import java.util.Comparator;

public class Town implements Comparable<Town>, Comparator<Town> {
	ArrayList<Town> towns = new ArrayList<Town>();
	ArrayList<Road> roads = new ArrayList<Road>();
	String name;
	int weight;
	Town previous;
	boolean visited;
	
	public Town(String name) {
		this.name = name;
		visited = false;
		weight = Integer.MAX_VALUE;
		previous = null;
	}
	
	public Town(Town t) {
		name = t.name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setVisited(boolean v) {
		visited = v;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public void setPrevious(Town p) {
		previous = p;
	}
	
	public Town getPrevious() {
		return previous;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public int compareTo(Town o) {
		// TODO Auto-generated method stub
		if(name == o.name)
			return 0;
		else 
			return 1;
		//return 0;
	}

	@Override
	public int compare(Town t1, Town t2) {
		// TODO Auto-generated method stub
		//return 0 is they are equal, 1 if the t1>t2 and -1 if t1<t2
		if(t1.weight == t2.weight)
			return 0;
		else if(t1.weight > t2.weight)
			return 1;
		else
			return -1;
		}
}
