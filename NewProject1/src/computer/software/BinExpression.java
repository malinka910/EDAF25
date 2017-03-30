package computer.software;

import computer.PC;
import computer.hardware.*;

public abstract class BinExpression implements Instruction {
	
	private Operand o1;
	private Operand o2;
	private Operand address;
	private final String opName;
	
	protected BinExpression(Operand o1, Operand o2, Operand address, String opName){
		this.o1 = o1;
		this.o2 = o2;
		this.address = address;
		this.opName = opName;
	}

	@Override
	public void execute(Memory context, PC pc){
		Word eval = expression(o1.getWord(context),o2.getWord(context));
		address.getWord(context).setValue(eval);
		//o1.getWord(context).mul(o2.getWord(context))
		pc.setPC(pc.getPC() + 1);
	}
	
	protected abstract Word expression(Word o1, Word o2);

	@Override
	public String print() {
		return opName + " " + o1.prt() + " " + o2.prt() + " " + address.prt();
	}

}
