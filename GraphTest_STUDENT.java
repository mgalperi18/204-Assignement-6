import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest_STUDENT {
	private GraphInterface<Town,Road> g;
	private Town[] t;

	@Before
	public void setUp() throws Exception {
		 g = new Graph();
		  t = new Town[12];
		  
		  for (int i = 1; i < 12; i++) {
			  t[i] = new Town("Town_" + i);
			  g.addVertex(t[i]);
		  }
		  
		  g.addEdge(t[2], t[3], 2, "Road_1");
		  g.addEdge(t[2], t[4], 4, "Road_2");
		  g.addEdge(t[2], t[5], 6, "Road_3");
		  g.addEdge(t[4], t[6], 1, "Road_4");
		  g.addEdge(t[4], t[7], 2, "Road_5");
		  g.addEdge(t[5], t[8], 3, "Road_6");
		  g.addEdge(t[7], t[9], 3, "Road_7");
		  g.addEdge(t[10], t[10], 4, "Road_8");
		  g.addEdge(t[9], t[10], 2, "Road_9");
		  g.addEdge(t[6], t[10], 5, "Road_10");
		  g.addEdge(t[11], t[11], 3, "Road_11");
		  g.addEdge(t[3], t[11], 6, "Road_12");
	}

	@After
	public void tearDown() throws Exception {
		g = null;
	}

	@Test
	public void testGetEdge() {
		assertEquals(new Road(t[3], t[12],6, "Road_12"), g.getEdge(t[3], t[12]));
		assertEquals(new Road(t[4], t[8],1, "Road_4"), g.getEdge(t[4], t[8]));
	}

	@Test
	public void testAddEdge() {
		assertEquals(false, g.containsEdge(t[3], t[5]));
		g.addEdge(t[3], t[5], 1, "Road_13");
		assertEquals(true, g.containsEdge(t[3], t[5]));
	}

	@Test
	public void testAddVertex() {
		Town newTown = new Town("Town_12");
		assertEquals(false, g.containsVertex(newTown));
		g.addVertex(newTown);
		assertEquals(true, g.containsVertex(newTown));
	}

	@Test
	public void testContainsEdge() {
		assertEquals(true, g.containsEdge(t[2], t[11]));
		assertEquals(false, g.containsEdge(t[3], t[5]));
	}

	@Test
	public void testContainsVertex() {
		assertEquals(true, g.containsVertex(new Town("Town_2")));
		assertEquals(false, g.containsVertex(new Town("Town_12")));
	}

	@Test
	public void testEdgeSet() {
		Set<Road> roads = g.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_1", roadArrayList.get(0));
		assertEquals("Road_10", roadArrayList.get(1));
		assertEquals("Road_11", roadArrayList.get(2));
		assertEquals("Road_12", roadArrayList.get(3));
		assertEquals("Road_2", roadArrayList.get(4));
		assertEquals("Road_8", roadArrayList.get(10));
	}

	@Test
	public void testEdgesOf() {
		Set<Road> roads = g.edgesOf(t[1]);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_1", roadArrayList.get(0));
		assertEquals("Road_2", roadArrayList.get(1));
		assertEquals("Road_3", roadArrayList.get(2));
	}
	
	@Test
	public void testRemoveEdge() {
		assertEquals(true, g.containsEdge(t[2], t[11]));
		g.removeEdge(t[2], t[11], 6, "Road_12");
		assertEquals(false, g.containsEdge(t[2], t[11]));
	}
	
	@Test
	public void testRemoveVertex() {
		assertEquals(true, g.containsVertex(t[2]));
		g.removeVertex(t[2]);
		assertEquals(false, g.containsVertex(t[2]));
	}

	@Test
	public void testVertexSet() {
		Set<Town> roads = g.vertexSet();
		assertEquals(true,roads.contains(t[1]));
		assertEquals(true, roads.contains(t[10]));
		assertEquals(true, roads.contains(t[11]));
		assertEquals(true, roads.contains(t[2]));
		assertEquals(true, roads.contains(t[3]));
	}

	 @Test
	  public void testTown_1ToTown_11() {
		  String beginTown = "Town_1", endTown = "Town_11";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = g.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = g.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_1 via Road_1 to Town_2 2 mi",path.get(0).trim());
			  assertEquals("Town_2 via Road_12 to Town_11 6 mi",path.get(1).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  
	  @Test
	  public void testTown1ToTown_10() {
		  String beginTown = "Town_1", endTown = "Town_10";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = g.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = g.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_1 via Road_2 to Town_3 4 mi",path.get(0).trim());
			  assertEquals("Town_3 via Road_5 to Town_8 2 mi",path.get(1).trim());
			  assertEquals("Town_8 via Road_9 to Town_10 2 mi",path.get(2).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  @Test
	  public void testTown_4ToTown_11() {
		  String beginTown = "Town_4", endTown = "Town_11";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = g.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = g.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_4 via Road_6 to Town_8 3 mi",path.get(0).trim());
			  assertEquals("Town_8 via Road_9 to Town_10 2 mi",path.get(1).trim());
			  assertEquals("Town_10 via Road_11 to Town_11 3 mi",path.get(2).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
}
