package computer.hardware;

import java.util.ArrayList;

import computer.software.Program;

public abstract class Memory {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Word[] memory;
	
	public Memory(int size){
		memory = new Word[size];
	}
	
	public Word get(int index){
		if(index > memory.length || index < 0){
			return null;
		}else{
			return memory[index];
		}
	}
	
	protected abstract void populate(int size);
	

}
