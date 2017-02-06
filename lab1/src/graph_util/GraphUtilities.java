package graph_util;
import graph.Graph;

import java.util.HashSet;
import java.util.Set;

public class GraphUtilities {
	public static <V,E> int nbrOfVertices(Graph<V,E> g) {
		int number = 0;
		for(Graph.Vertex<V, E> v : g){
			number++;
		}
		return number;
	}
	
	public static <V,E> int nbrOfEdges(Graph<V,E> g, boolean directed) {
		int number = 0;
		for(Graph.Vertex<V, E> v : g){
			for(Graph.Edge<V, E> e : v){
				number++;
			}
		}
		if(directed){
			return number;
		}else{
			return number/2;
		}	
	}
	
	public static <V,E> boolean edgeBetween(Graph.Vertex<V,E> from, 
										Graph.Vertex<V,E> to) {
		for(Graph.Edge<V, E> e : from){
			if(e.destination() == to){
				return true;
			}
		}
		return false;
	}
}
