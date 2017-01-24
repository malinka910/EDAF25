package dfs_applications;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import graph.Graph;


public class DFS  {
	
	public static <V,E> void dfs(Graph<V,E> g) {
		g.unvisit();
		for (Graph.Vertex<V,E> v : g) {
			if (!v.isVisited()) {
				dfs(v);
			}
		}
	}
	
	private static <V,E> void dfs(Graph.Vertex<V,E> v) {
		v.visit();
		for (Graph.Edge<V,E> e : v) {
			Graph.Vertex<V,E> w = e.destination();
			
			if (!w.isVisited()) {
				//if(w = u){ return true;}
				dfs(w);
			}
		}
	}
	
	public static <V,E> boolean isConnected(Graph<V,E> g) {
		g.unvisit();
		int i = 0;
		for (Graph.Vertex<V,E> v : g) {
			if (!v.isVisited()) {
				dfs(v);
				i++;
				if(i>1){
					return false;
				}
			}
		}
		return true;
	}
	
	public static <V,E> int nbrOfComponents(Graph<V,E> g) {
		g.unvisit();
		int i = 0;
		for (Graph.Vertex<V,E> v : g) {
			if (!v.isVisited()) {
				dfs(v);
				i++;
			}
		}
		return i;
	}
	
	public static <V,E> boolean pathExists(Graph<V,E> g,
			Graph.Vertex<V,E> v, Graph.Vertex<V,E> u) {
		if(v == u){
			return true;
		}
		g.unvisit();
		return dfsPath(v , u);
		
		
	}
	
	private static <V,E> boolean dfsPath(Graph.Vertex<V,E> v , Graph.Vertex<V,E> u){
		v.visit();
		boolean path = false;
		for (Graph.Edge<V,E> e : v) {
			Graph.Vertex<V,E> w = e.destination();
			
			if (!w.isVisited()) {
				if(w == u){ 
					return true;
				}
				path = dfsPath(w , u);
			}
		}
		return path;
	}
		
	public static <V,E> List<Graph.Vertex<V,E>> findPath(Graph<V,E> g,
			Graph.Vertex<V,E> v, Graph.Vertex<V,E> u) {
		return null;
	}
	

}

