package computer;

import computer.hardware.Memory;
import computer.software.Program;

public class Computer {
	
	private Memory memory;
	private Program program;
	private PC pc;
	
	public Computer(Memory memory){
		this.memory = memory;
		pc = new PC();
	}
	
	public void load(Program program){
		this.program = program;
		program.setContext(memory, pc);
	}
	
	public void run(){
		while(pc.getPC() >= 0){
			program.executeLine(pc.getPC());	
		}
	}

}
