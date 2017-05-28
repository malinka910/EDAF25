package computer.software;

import computer.PC;
import computer.hardware.*;

public class Copy implements Instruction {
	
	private Operand o1;
	private Operand address;
	
	public Copy(Operand o1, Operand address){
		this.o1 = o1;
		this.address = address;
	}

	@Override
	public void execute(Memory context, PC pc) {
		address.getWord(context).setValue(o1.getWord(context));
		pc.setPC(pc.getPC()+1);
	}

	@Override
	public String print() {
		return "CPY " + o1.prt() + " " + address.prt();
	}

}
