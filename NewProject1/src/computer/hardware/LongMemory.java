package computer.hardware;

public class LongMemory extends Memory {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LongMemory(int size) {
		super(size);
		populate(size);
	}
	
	protected void populate(int size){
		for(int i = 0 ; i < size ; i++){
			this.memory[i] = new LongWord(0);
			//this.set(i, new LongWord(0));
		}
	}

	

}
