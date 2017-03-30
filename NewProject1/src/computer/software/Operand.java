package computer.software;

import computer.hardware.*;

public interface Operand {
	
	public Word getWord(Memory context);
	
	public String prt();

}
