package computer.software;

import computer.PC;
import computer.hardware.Memory;

public class JumpEq implements Instruction {
	
	private Operand o1;
	private Operand o2;
	private long jumpTo;
	
	protected JumpEq(long jumpTo, Operand o1, Operand o2){
		this.o1 = o1;
		this.o2 = o2;
		this.jumpTo = jumpTo;
	}

	@Override
	public void execute(Memory context, PC pc) {
		if(o1.getWord(context).eql(o2.getWord(context))){
			pc.setPC((int)jumpTo);
		}else{
			pc.setPC(pc.getPC()+1);
		}
	}

	@Override
	public String print() {
		return "JEQ " + jumpTo + " " + o1.prt() + " " + o2.prt();
	}

}
