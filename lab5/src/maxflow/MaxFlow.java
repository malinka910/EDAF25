package maxflow;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class MaxFlow {
	
	private int[][] network;
	
	public MaxFlow(String fileName){
		File file = new File(fileName);
		int nodeNbr;
		int vertexNbr;
		try{
			//make a scanner from file
			Scanner scan = new Scanner(file);
			//get initial values from file
			nodeNbr = scan.nextInt();
			vertexNbr = scan.nextInt();
			//make matrix with initial node value
			network = new int[nodeNbr][nodeNbr];
			//use initial vertex value to decide nbr of iterations
			for(int v = 0; v < vertexNbr; v++){
				while(scan.hasNext()){
					int i = scan.nextInt();
					int j = scan.nextInt();
					int capacity = scan.nextInt();
					//System.out.println(i + " " + j + " " + capacity);
					if(capacity >= 0){
						network[i][j] = capacity;
					}else{
						network[i][j] = Integer.MAX_VALUE;
					}
				}
			}		
			scan.close();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void printMatrix(){
		for(int i = 0 ; i < network.length; i++){
			StringBuilder builder = new StringBuilder();
			for(int j = 0 ; j < network.length; j++){
				int val = network[i][j];
				builder.append(val + " ");
			}
			System.out.println(builder.toString());
		}
	}
	/**
	 * Calculate the max flow (or as I like to call it: capacity for flux) 
	 * of the system. 
	 * @return the flux capacity of the system. 
	 * TODO:
	 * 1) flow/fluxCapacity = 0;
	 * 2) while(augmenting path exists){add this path-flow to flow}
	 * 3) return flow/fluxCapacity;
	 */
	public int fluxCapacity(){
		int fluxCapacity = 0;
		int pathFlux = 1;
		int[][] residualMatrix = new int[network.length][network.length];
		for(int i = 0 ; i < network.length ; i++){
			for(int j = 0 ; j < network.length ; j++){
				residualMatrix[i][j] = network[i][j];
			}
		}
		while(pathFlux != 0){
			printResMatrix(residualMatrix);
			pathFlux = bfs(residualMatrix);
			fluxCapacity = fluxCapacity + pathFlux;
		}
		return fluxCapacity;
	}
	
	public int bfs(int[][] graph){
		//keep track of visited
		boolean[] visited = new boolean[graph.length];
		//vertex queue for breadth first search
		LinkedList<Integer> vertexQueue = new LinkedList<Integer>();
		//path list
		int[] path = new int[graph.length];
		int bottleNeck = Integer.MAX_VALUE;
		int source = 0;
		int sink = graph.length-1;
		vertexQueue.add(source);
		visited[source] = true;
		path[source] = -1;
		while(vertexQueue.size()>0){
			int current = vertexQueue.poll();
			//go through current node's neighbors and ad unvisited to queue
			//also keep track of path
			for(int n = 0; n<graph.length ; n++){
				if( !visited[n] && (graph[current][n] > 0) ){
					path[n] = current;
					visited[n] = true;
					vertexQueue.add(n);	
				}
			}
		}
		if(visited[sink]){
			int current = sink;
			int parent = path[current];
			while(parent != -1){
				//System.out.println(parent);
				//System.out.println(graph[parent][current]);
				if(graph[parent][current] <= bottleNeck){
					bottleNeck = graph[parent][current];
				}
				current = parent;
				parent = path[current];
			}
			System.out.println("bottle neck calculated");
			int currentFlow = sink;
			int parentFlow = path[currentFlow];
			while(parentFlow != -1){
				System.out.println("update the res matrix");
				graph[parentFlow][currentFlow] = graph[parentFlow][currentFlow] - bottleNeck;
				currentFlow = parentFlow;
				parentFlow = path[currentFlow];
			}
			System.out.println("bottle neck : " + bottleNeck);
			return bottleNeck;
		}else{
			System.out.println("bottle neck : " + 0);
			return 0;
		}
	}
	
	public void printResMatrix(int[][] residualMatrix){
		for(int i = 0 ; i < residualMatrix.length; i++){
			StringBuilder builder = new StringBuilder();
			for(int j = 0 ; j < residualMatrix.length; j++){
				int val = residualMatrix[i][j];
				if(val == Integer.MAX_VALUE){
					builder.append("∞ ");
				}else{
					builder.append(val + " ");
				}
			}
			System.out.println(builder.toString());
		}
	}
	

}
