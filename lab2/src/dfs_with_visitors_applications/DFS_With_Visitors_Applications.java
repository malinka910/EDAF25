package dfs_with_visitors_applications;
import graph.Graph;
import dfs_with_visitor.*;

public class DFS_With_Visitors_Applications {
	
	public static <V,E> boolean isConnected(Graph<V,E> g) {
		g.unvisit();
		DFS.dfs(g.iterator().next(), new VertexVisitor<V,E>());
		for (Graph.Vertex<V,E> v : g) {
			if (! v.isVisited()) {
				return false;
			}
		}
		return true;
	}
	
	public static <V,E> int componentSize(Graph<V,E> g,
								Graph.Vertex<V,E> v) {
		g.unvisit();
		CountingVisitor<V,E> cv= new CountingVisitor<V,E>();
		DFS.dfs(v,cv);
		return cv.count;
	}
	
	/**
	 * Check for a path in Graph @param g, from Vertex @param v to Vertex @param u
	 * @return true if path exists. 
	 */
	public static <V,E> boolean pathExists(Graph<V,E> g, 
			Graph.Vertex<V,E> v, Graph.Vertex<V,E> u) {
		
		//clear previous visited-data
		g.unvisit();
		//make a visitor 
		PathCheckVisitor<V,E> pc = new PathCheckVisitor<V,E>(u);
		//go through all of the vertices in the graph. break if find what looking for
		DFS.dfs(v , pc);
		// return what the visitor found
		return pc.path;
		
	}
	
	static class CountingVisitor<V,E> extends VertexVisitor<V,E> {
		private int count;
		
		public CountingVisitor() {
			super();
			count = 0;
		}
		
		public void preVisit(Graph.Vertex<V,E> v) {
			count++;
		}		
	}
	/**
	 * Hold destination Vertex, check every vertex in dfs against destination.
	 * if destination reached: set path to true and break dfs.
	 * @param <V> data type for Vertex.value parameter
	 * @param <E> data type for Edge.value parameter
	 */
	static class PathCheckVisitor<V,E> extends VertexVisitor<V,E> {
		private boolean path;
		Graph.Vertex<V,E> u;
		
		/**
		 * Takes a destination Vertex object ( @param u ). 
		 * at construction and default path setting is false.
		 */
		public PathCheckVisitor(Graph.Vertex<V,E> u){
			super();
			path = false;
			this.u = u;
		}
		/**
		 * Takes a arbitrary vertex v and checks if it's the vertex we're looking for.
		 * Breaks the dfs if the right vertex was found.
		 */
		public void preVisit(Graph.Vertex<V,E> v){
			if(v == u){
				path = true;
				return;
			}
		}
		
	}
	
}
