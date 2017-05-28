package computer.software;

import computer.PC;
import computer.hardware.Memory;

public class Halt implements Instruction {
	
	public Halt(){
		// anin't got no operands
	}

	@Override
	public void execute(Memory context, PC pc) {
		pc.setPC(-1);
	}

	@Override
	public String print() {
		return "HLT";
	}

}
