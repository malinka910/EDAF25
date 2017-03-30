package computer.software;

import computer.PC;
import computer.hardware.Address;
import computer.hardware.Memory;
import computer.hardware.Word;

public class Add implements Instruction {
	
	private Operand o1;
	private Operand o2;
	private Operand address;
	
	public Add(Operand o1, Operand o2, Operand address){
		this.o1 = o1;
		this.o2 = o2;
		this.address = address;
	}

	@Override
	public void execute(Memory context, PC pc) {
		address.getWord(context).setValue(o1.getWord(context).add(o2.getWord(context)));
		pc.setPC(pc.getPC() + 1);
	}

	@Override
	public String print() {
		return "ADD " + o1.prt() + " " + o2.prt() + " " + address.prt();
	}

}
