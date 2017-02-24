package dijkstra;
import graph.Graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.Map;
import java.util.PriorityQueue;
//import java.util.Set;

// extends Comparator<E>
public class Dijkstra<V,E> {
	
	private Comparator<E> comparatorE;
	
	/**
	 * A generalized implementation of Dijkstra's algorithm.
	 * @param g
	 * @param from
	 * @param to
	 * @param cmp
	 * @param adder
	 * @return
	 */
	public E shortestPath(Graph<V,E> g, Graph.Vertex<V,E> from, Graph.Vertex<V,E> to, Comparator<E> cmp, Adder<E> adder){
		this.comparatorE = cmp;	
		PriorityQueue<PrioQueueEntry> prioQueue = new PriorityQueue<PrioQueueEntry>();
		HashMap<Graph.Vertex<V,E>,E> vertexMap = new HashMap<Graph.Vertex<V,E>,E>();
		HashSet<Graph.Vertex<V,E>> doneSet = new HashSet<Graph.Vertex<V,E>>();
		//pq.offer({v,0})
		prioQueue.add(new PrioQueueEntry(from,adder.getZero()));
		// vertexMap.put(v,0)
		vertexMap.put(from, adder.getZero());
		// while
		while(!prioQueue.isEmpty()){
			//actVertex
			PrioQueueEntry current = prioQueue.poll();
			if(current.vertex==to){
				return current.tempShortestPath;
			}
			// If not in doneSet, put in done set and do the things
			// Else skip over and go to next node
			if(!doneSet.contains(current.vertex)){
				doneSet.add(current.vertex);
				for(Graph.Edge<V,E> e : current.vertex){
					Graph.Vertex<V,E> next = e.destination();
					E newDist = adder.add(e.getValue(), current.tempShortestPath);
					if( (!vertexMap.containsKey(next)) || cmp.compare(newDist , vertexMap.get(next))<0 ){
						//pq.offer({next,newDist})
						prioQueue.offer(new PrioQueueEntry(next,newDist));
						// vertexMap.put(next,newDist)
						vertexMap.put(next,newDist);
					}
				}
			}
		}
		return null;//TODO
	}
	
	
	/**
	 * A class to describe the elements in the priority queue
	 * @author Greg
	 */
	private class PrioQueueEntry implements Comparable<PrioQueueEntry>, Comparator<PrioQueueEntry>{
		
		Graph.Vertex<V, E> vertex;
		E tempShortestPath;
		
		public PrioQueueEntry(Graph.Vertex<V, E> vertex, E tempShortestPath){
			this.vertex=vertex;
			this.tempShortestPath=tempShortestPath;
		}
		
		@Override
		public int compare(PrioQueueEntry o1, PrioQueueEntry o2) {
			return comparatorE.compare(o1.tempShortestPath, o2.tempShortestPath);
		}

		@Override
		public int compareTo(Dijkstra<V, E>.PrioQueueEntry o) {
			return compare(this,o);
		}
			
	}
	
	/**
	 * This was redundant, the queue entry should implement the comparable/comparator interface.
	 * @author Greg
	 */
//	private class PrioQueueEntryComapator implements Comparator<PrioQueueEntry>{
//
//		@Override
//		public int compare(PrioQueueEntry o1, PrioQueueEntry o2) {
//			return comparator.compare(o1.tempShortestPath, o2.tempShortestPath);
//		}
//
//	}
		
}
