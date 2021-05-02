import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownGraphManagerTest_STUDENT {
	private TownGraphManagerInterface g;
	private String[] t;
	  
	@Before
	public void setUp() throws Exception {
		  g = new TownGraphManager();
		  t = new String[12];
		  
		  for (int i = 1; i < 12; i++) {
			  t[i] = "Town_" + i;
			  g.addTown(t[i]);
		  }
		  
		  g.addRoad(t[1], t[2], 2, "Road_1");
		  g.addRoad(t[1], t[3], 4, "Road_2");
		  g.addRoad(t[1], t[5], 6, "Road_3");
		  g.addRoad(t[3], t[7], 1, "Road_4");
		  g.addRoad(t[3], t[8], 2, "Road_5");
		  g.addRoad(t[4], t[8], 3, "Road_6");
		  g.addRoad(t[6], t[9], 3, "Road_7");
		  g.addRoad(t[9], t[10], 4, "Road_8");
		  g.addRoad(t[8], t[10], 2, "Road_9");
		  g.addRoad(t[5], t[10], 5, "Road_10");
		  g.addRoad(t[10], t[11], 3, "Road_11");
		  g.addRoad(t[2], t[11], 6, "Road_12");
		 
	}

	@After
	public void tearDown() throws Exception {
		g = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = g.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_11", roads.get(2));
		assertEquals("Road_12", roads.get(3));
		g.addRoad(t[4], t[11], 1,"Road_13");
		roads = g.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_11", roads.get(2));
		assertEquals("Road_12", roads.get(3));
		assertEquals("Road_13", roads.get(4));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("Road_12", g.getRoad(t[2], t[11]));
		assertEquals("Road_4", g.getRoad(t[3], t[7]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, g.containsTown("Town_12"));
		g.addTown("Town_12");
		assertEquals(true, g.containsTown("Town_12"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, g.containsTown("Town_12"));
		g.addTown("Town_12");
		ArrayList<String> path = g.getPath(t[1],"Town_12");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, g.containsTown("Town_2"));
		assertEquals(false, g.containsTown("Town_12"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, g.containsRoadConnection(t[2], t[11]));
		assertEquals(false, g.containsRoadConnection(t[3], t[5]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = g.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_11", roads.get(2));
		assertEquals("Road_8", roads.get(10));
		assertEquals("Road_9", roads.get(11));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, g.containsRoadConnection(t[2], t[11]));
		g.deleteRoadConnection(t[2], t[11], "Road_12");
		assertEquals(false, g.containsRoadConnection(t[2], t[11]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, g.containsTown("Town_2"));
		g.deleteTown(t[2]);
		assertEquals(false, g.containsTown("Town_2"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = g.allTowns();
		assertEquals("Town_1", roads.get(0));
		assertEquals("Town_10", roads.get(1));
		assertEquals("Town_11", roads.get(2));
		assertEquals("Town_2", roads.get(3));
		assertEquals("Town_8", roads.get(9));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = g.getPath(t[1],t[11]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Road_1 to Town_2 2 mi",path.get(0).trim());
		  assertEquals("Town_2 via Road_12 to Town_11 6 mi",path.get(1).trim());

	}
	
	@Test
	public void testGetPathA() {
		ArrayList<String> path = g.getPath(t[1],t[10]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Road_2 to Town_3 4 mi",path.get(0).trim());
		  assertEquals("Town_3 via Road_5 to Town_8 2 mi",path.get(1).trim());
		  assertEquals("Town_8 via Road_9 to Town_10 2 mi",path.get(2).trim());
	}
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = g.getPath(t[1],t[6]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Road_2 to Town_3 4 mi",path.get(0).trim());
		  assertEquals("Town_3 via Road_5 to Town_8 2 mi",path.get(1).trim());
		  assertEquals("Town_8 via Road_9 to Town_10 2 mi",path.get(2).trim());
		  assertEquals("Town_10 via Road_8 to Town_9 4 mi",path.get(3).trim());
		  assertEquals("Town_9 via Road_7 to Town_6 3 mi",path.get(4).trim());

	}


}
