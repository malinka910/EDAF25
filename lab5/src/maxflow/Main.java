package maxflow;

public class Main {

	public static void main(String[] args) {
		MaxFlow max = new MaxFlow("rail.txt");
		System.out.println("Matrix from file: ");
		max.printMatrix();
		System.out.println("");
		max.fluxCapacity();
		//System.out.println("");
		//max.printMatrix();
	}

}
