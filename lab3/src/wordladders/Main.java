package wordladders;

import java.io.*;
import java.util.*;


public class Main {
	
	
	public static void processRequests(GraphStrategy strategy, String wordfile, String infile) {
		
		
		/* Make the graph */
		MapImplementation graph = new MapImplementation(strategy, wordfile);
		
		/* Make a list of pairs */
		ArrayList<String[]> pairs = new ArrayList<String[]>();
		Scanner scan = null;
		try{
			scan = new Scanner(new File(infile));
		}catch(FileNotFoundException e){
			System.err.println("Couldn't open file: " + infile);
			System.exit(1);
		}
		while(scan.hasNextLine()){
			if(scan.hasNext()){
				String[] pair = new String[2];
				pair[0] = scan.next();
				pair[1] = scan.next();
				pairs.add(pair);
			}
		}
		scan.close();
		
		
		
		
		/*
		 * 1) Calculate the shortest path
		 * THEN
		 * 2) Print the pair of words 
		 * AND THEN
		 * 3) print the shortest path calculation
		 */
		for(String[] p: pairs){
			int path = ShortestPath.shortestPath(graph, p[0], p[1]);
			System.out.println(path);
		}

	}

}
