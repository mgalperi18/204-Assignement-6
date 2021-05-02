
public class Road implements Comparable<Road>{
	
	Town source, destination;
	String roadName;
	int distance;
	
	public Road(Town s, Town des, int dis, String n) {
		source = s;
		destination = des;
		distance = dis;
		roadName = n;		
	}
	
	public Road(Town s, Town des, String n) {
		source = s;
		destination = des;
		distance = 1;
		roadName = n;		
	} 
	
	//getters and setters
	public String getName() {
		return this.roadName;
	}
	public void setName(String name) {
		this.roadName =  name;
	}
	
	public int getDistance() {
		return this.distance;
	}
	public void setDistance(int d) {
		distance = d;
	}
	
	
	public boolean contains(Town t) {
		if(source ==  t || destination == t) 
			return true;
		else 
			return false;
		
	}
	
	@Override
	public boolean equals(Object o) {
		Road r = (Road)o;
		
		if( compareTo(r) == 0)
			return true;
		else 
			return false;
	}
	
	@Override
	public int compareTo(Road o) {
		// TODO Auto-generated method stub
		if(roadName == o.getName())
			return 0;
		else 
			return 1;
		//return 0;
	}

}
