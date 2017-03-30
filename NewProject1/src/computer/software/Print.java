package computer.software;

import computer.PC;

import computer.hardware.*;



public class Print implements Instruction {
	
	private Operand o;
	
	public Print(Operand o){
		this.o = o;
	}

	

	

	@Override
	public void execute(Memory context, PC pc) {
		pc.setPC(pc.getPC() + 1);
		if(o instanceof Address){
			System.out.println(((Address) o).getWord(context).prt());
		}else{
			System.out.println(o.prt());
		}
	}

	@Override
	public String print() {
		return "PRT " + o.prt();
	}

}
