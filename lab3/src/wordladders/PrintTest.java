package wordladders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class PrintTest {

	public static void main(String[] args) {
		
		/* Constructor Test */
		
//		MapImplementation graph = new MapImplementation(new OneLetterDifference(), "words-107.txt");
//		
//		Set<String> keySet = graph.wordGraph.keySet();
//		System.out.println(keySet.size());
//		for(String s : keySet){
//				System.out.print(s + ": " );
//				for(String neighbor: graph.wordGraph.get(s)){
//					System.out.print(neighbor + ", ");
//				}
//				System.out.println("");
//		}
		
		
		
		
		/* Building Pairs Test */
		
		MapImplementation graph = new MapImplementation(new OneLetterDifference() , "words-5757.txt");

		ArrayList<String[]> pairs = new ArrayList<String[]>();
		Scanner scan = null;
		String infile = "words-5757-test.in";
		try{
			scan = new Scanner(new File(infile));
			while(scan.hasNextLine()){
					String[] pair = new String[2];
					pair[0] = scan.next();
					pair[1] = scan.next();
					System.out.println(pair[0] + " " + pair[1]);
					pairs.add(pair);
			}
		}catch(FileNotFoundException e){
			System.err.println("Couldn't open file: " + infile);
			System.exit(1);
		}
		for(String[] p: pairs){
			//String[] p = pairs.get(4);
			int path = ShortestPath.shortestPath(graph, p[0], p[1]);
			System.out.println("[ " + p[0] + " : " + p[1] + " ] -> " + path);
		}
		
		
	}

}
