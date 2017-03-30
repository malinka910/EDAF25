package computer.software;

import computer.hardware.Memory;
import computer.PC;

public interface Instruction {
		public void execute(Memory context, PC pc);	
		public String print();
}
