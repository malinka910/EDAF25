package wordladders;

import java.io.*;
import java.util.*;


public class MapImplementation implements SimpleGraph {
	
	HashMap<String, Set<String>> wordGraph;
	GraphStrategy criteria;
	
	public MapImplementation(GraphStrategy criteria, String fileName){
		this.criteria = criteria;
		this.wordGraph = new HashMap<String, Set<String>>();
		Scanner scan = null;
		try{
			scan = new Scanner(new File(fileName));
		}catch(FileNotFoundException e){
			System.err.println("Couldn't open file: " + fileName);
			System.exit(1);
		}
		while(scan.hasNext()){
			String w = scan.nextLine();
			HashSet<String> emptySet = new HashSet<String>();
			wordGraph.put(w, emptySet);
		}
		scan.close();
		for(String w1 : wordGraph.keySet()){
			for(String w2 : wordGraph.keySet()){
				//criteria.adjacent(w1, w2)
				//!(w1==w2) || 
				if(criteria.adjacent(w1, w2)){
					wordGraph.get(w1).add(w2);
				}
			}
		}
		
	}

	@Override
	public Set<String> adjacentTo(String s) {
		Set<String> neighbors = new HashSet<String>();
		neighbors.addAll(wordGraph.get(s));
		return neighbors;
	}

}
