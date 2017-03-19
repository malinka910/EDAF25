package computer.hardware;

import java.util.ArrayList;

import computer.software.Program;

public abstract class Memory extends ArrayList<Word> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int sizeMax;
	private Program program;
	
	public Memory(int size){
		super(size);
		this.sizeMax = size;
	}
	
	@Override
	public Word set(int index, Word word){
		if(index > sizeMax || index < 0){
			return null;
		}else{
			return super.set(index, word);
		}
	}

}
