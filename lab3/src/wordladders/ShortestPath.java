package wordladders;
import java.util.*;


public class ShortestPath {
	public static int shortestPath(SimpleGraph g, String source, String dest) {
		// 4, 6, 7, 8, 10
		// I'm assuming that source and destination are actually in the graph 
		// String u = source;
		
		int distance = 0;
		
		Set<String> visited = new HashSet<String>();
		Set<String> actLevel = new HashSet<String>();
		Set<String> nextLevel = new HashSet<String>();
		
		visited.add(source);
		actLevel.add(source);
		while(!actLevel.isEmpty()){
			nextLevel.clear();
			for(String w : actLevel){
				if(w.equals(dest)){
					return distance;
				}
				for(String n : g.adjacentTo(w)){
					if(!visited.contains(n)){
						visited.add(n);
						nextLevel.add(n);
					}
				}
			}
			distance++;
			actLevel.clear();
			actLevel.addAll(nextLevel);
		}
		
		return -1;
	}

}
