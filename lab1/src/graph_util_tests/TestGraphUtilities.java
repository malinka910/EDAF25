package graph_util_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import graph.Graph;
import digraph.DiGraph;
import graph_util.GraphUtilities;

public class TestGraphUtilities {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNbrOfVerticesNoVertices() {
		Graph<String,Integer> g = new DiGraph<String,Integer>();
		assertEquals(0, GraphUtilities.nbrOfVertices(g));
	}
	
	@Test
	public void testNbrOfVerticesOneVertex() {
		Graph<String,Integer> g = new DiGraph<String,Integer>();
		g.addVertex("a");
		assertEquals(1, GraphUtilities.nbrOfVertices(g));
	}
	
	@Test
	public void testNbrOfVerticesTwoVertices() {
		Graph<String,Integer> g = new DiGraph<String,Integer>();
		g.addVertex("a");
		g.addVertex("b");
		assertEquals(2, GraphUtilities.nbrOfVertices(g));
	}
	
	@Test
	public void testNbrOfVerticesFiveVertices() {
		Graph<String,Integer> g = new DiGraph<String,Integer>();
		Graph.Vertex<String,Integer> v1 = g.addVertex("a");
		Graph.Vertex<String,Integer> v2 = g.addVertex("b");
		Graph.Vertex<String,Integer> v3 = g.addVertex("c");
		Graph.Vertex<String,Integer> v4 = g.addVertex("d");
		Graph.Vertex<String,Integer> v5 = g.addVertex("e");
		g.addEdge(1,v1,v2);
		g.addEdge(4,v2,v3);
		g.addEdge(3,v3,v4);
		g.addEdge(7,v4,v5);
		g.addEdge(2,v5,v1);
		assertEquals(5, GraphUtilities.nbrOfVertices(g));
	}
	
	@Test
	public void testNbrOfEdges() {
		Graph<String,Integer> g = new DiGraph<String,Integer>();
		boolean directed = true;
		Graph.Vertex<String,Integer> v1 = g.addVertex("a");
		Graph.Vertex<String,Integer> v2 = g.addVertex("b");
		Graph.Vertex<String,Integer> v3 = g.addVertex("c");
		Graph.Vertex<String,Integer> v4 = g.addVertex("d");
		
		//no edges
		assertEquals(0, GraphUtilities.nbrOfEdges(g, directed));
		assertEquals(0, GraphUtilities.nbrOfEdges(g, !directed));
		
		//one arrow : two vertices (1 , 2)
		g.addEdge(1,v1,v2);
		assertEquals(1, GraphUtilities.nbrOfEdges(g, directed));
		
		//two arrows : two vertices (1 , 2)
		g.addEdge(1,v2,v1);
		assertEquals(2, GraphUtilities.nbrOfEdges(g, directed));
		assertEquals(1, GraphUtilities.nbrOfEdges(g, !directed));
		
		//three arrows : three vertices (1 , 2 , 3)
		g.addEdge(4,v2,v3);
		assertEquals(3, GraphUtilities.nbrOfEdges(g, directed));
		
		//four arrows : three vertices (1 , 2 , 3)
		g.addEdge(4,v3,v2);
		assertEquals(4, GraphUtilities.nbrOfEdges(g, directed));
		assertEquals(2, GraphUtilities.nbrOfEdges(g, !directed));
		
		//five arrows : three vertices (1 , 2 , 3)
		g.addEdge(4,v1,v3);
		assertEquals(5, GraphUtilities.nbrOfEdges(g, directed));
		
		//six arrows : three vertices (1 , 2 , 3)
		g.addEdge(4,v3,v1);
		assertEquals(6, GraphUtilities.nbrOfEdges(g, directed));
		assertEquals(3, GraphUtilities.nbrOfEdges(g, !directed));
		
		//seven arrows : four vertices (1 , 2 , 3 , 4)
		g.addEdge(3,v1,v4);
		assertEquals(7, GraphUtilities.nbrOfEdges(g, directed));
		
		//eight arrows : four vertices (1 , 2 , 3 , 4)
		g.addEdge(3,v2,v4);
		assertEquals(8, GraphUtilities.nbrOfEdges(g, directed));
		
		//nine arrows : four vertices (1 , 2 , 3 , 4)
		g.addEdge(3,v3,v4);
		assertEquals(9, GraphUtilities.nbrOfEdges(g, directed));
		
		//twelve arrows : four vertices (1 , 2 , 3 , 4)
		g.addEdge(3,v4,v1);
		g.addEdge(3,v4,v2);
		g.addEdge(3,v4,v3);
		assertEquals(12, GraphUtilities.nbrOfEdges(g, directed));
		assertEquals(6, GraphUtilities.nbrOfEdges(g, !directed));
		
	}
	
	@Test
	public void testNbrOfEdgesLarge() {
		Graph<String,Integer> g = new DiGraph<String,Integer>();
		int n = 99;	//number of nodes - 1
		boolean directed = true;
		
		Graph.Vertex<String,Integer> v = g.addVertex("alpha");
		for(int i = 1 ; i <= n ; i++){
			Graph.Vertex<String,Integer> v1 = g.addVertex("a");
			Graph.Vertex<String,Integer> v2 = g.addVertex("b");
			Graph.Vertex<String,Integer> v3 = g.addVertex("c");
			
			// 1 <-> 2
			g.addEdge(3,v2,v1);
			g.addEdge(3,v1,v2);
			// 2 <-> 3
			g.addEdge(3,v2,v3);
			g.addEdge(3,v3,v2);
			// 3 <-> 1
			g.addEdge(3,v1,v3);
			g.addEdge(3,v3,v1);
			// v <-> 1
			g.addEdge(3,v,v1);
			g.addEdge(3,v1,v);
			// v <-> 2
			g.addEdge(3,v,v2);
			g.addEdge(3,v2,v);
			// v <-> 3
			g.addEdge(3,v,v3);
			g.addEdge(3,v3,v);
			//new v
			v = v3;
			assertEquals(12*i, GraphUtilities.nbrOfEdges(g, directed));
			assertEquals(6*i, GraphUtilities.nbrOfEdges(g, !directed));
		}
		
	}
	
	@Test
	public void testEdgeBetween() {
		Graph<String,Integer> g = new DiGraph<String,Integer>();
		Graph.Vertex<String,Integer> v1 = g.addVertex("a");
		Graph.Vertex<String,Integer> v2 = g.addVertex("b");
		Graph.Vertex<String,Integer> v3 = g.addVertex("c");
		
		// no edges
		assertFalse(GraphUtilities.edgeBetween(v1,v2));
		
		// add an edge 
		g.addEdge(3,v2,v1);
		g.addEdge(3,v1,v2);
		assertTrue(GraphUtilities.edgeBetween(v1,v2));
		assertFalse(GraphUtilities.edgeBetween(v2,v3));
		
		// add a bunch of edges and see if it'll catch the sought edge isn't there
		for(int i = 0 ; i < 30 ; i++){
			g.addEdge(3,v2,v1);
		}
		assertFalse(GraphUtilities.edgeBetween(v2,v3));
		
		// put the edge in and check again
		g.addEdge(3,v2,v3);
		assertTrue(GraphUtilities.edgeBetween(v2,v3));
	}
	
	@Test
	public void testEdgeBetweenNoEdge() {
		Graph<String,Integer> g = new DiGraph<String,Integer>();
		Graph.Vertex<String,Integer> v1 = g.addVertex("a");
		Graph.Vertex<String,Integer> v2 =g.addVertex("b");
		assertFalse(GraphUtilities.edgeBetween(v1,v2));
	}
	

}
