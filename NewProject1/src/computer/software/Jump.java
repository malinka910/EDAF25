package computer.software;

import computer.PC;
import computer.hardware.Memory;

public class Jump implements Instruction {
	
	private long jumpTo;

	protected Jump(long jumpTo) {
		this.jumpTo = jumpTo;
	}

	@Override
	public void execute(Memory context, PC pc) {
		pc.setPC((int)jumpTo);
	}

	@Override
	public String print() {
		return "JMP " + jumpTo;
	}

}
