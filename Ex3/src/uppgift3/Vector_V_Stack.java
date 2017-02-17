package uppgift3;

import java.util.*;

public class Vector_V_Stack {

	public static void main(String[] args) {
		
		
		Vector<Integer> vector = new Vector<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 1; i <= 10 ; i++){
			vector.add(i);
			stack.add(i); // ISP issue, shouldn't be able to do this
		}
		vector.add(3, 12); // LSP
		stack.add(3, 12);  // LSP 
		//LSP as long as I can do this, it should produce the same results
		
		for(int i = 0; i < vector.size() ; i++){
			System.out.print(vector.get(i) + " ");
		}
		System.out.println("");
		
		for(int i = 0; i < 10 ; i++){
			System.out.print(stack.get(i) + " ");
		}
		
		MyStack<Integer> myStack = new MyStack<Integer>();
		
		System.out.println("");
		for(int i = 1; i <= 10 ; i++){
			myStack.push(i);
			System.out.print(myStack.peek() + " ");
		}
		
		System.out.println("");
		for(int i = 0; i < 10 ; i++){
			System.out.print(myStack.pull() + " ");
		}
		
		System.out.println("");
		for(int i = 0; i < 10 ; i++){
			System.out.print(myStack.pull() + " ");
		}
	}
	
	

}

// SRP single responsibility principle... I mean... it's got the methods to take on other responsibilities
// DIP it really should implement an interface instead of extending something.
// ISP interface segregation principle
// LSP Liskov substitution principle
