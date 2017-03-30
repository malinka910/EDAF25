package computer.software;

import java.util.ArrayList;

import computer.PC;
import computer.hardware.Memory;

/**
 * The Program class contains an ArrayList instead of inheriting from ArrayList to make 
 * it easier for the Instruction objects to have access to the program context (i.e. the 
 * specific memory object and the specific PC (program counter)). 
 */
public abstract class Program {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Memory context;
	private ArrayList<Instruction> program;
	private PC pc;
	
	public Program(){
		program = new ArrayList<Instruction>();
	}
	
	public void add(Instruction instruction){
		program.add(instruction);
	}
	
	public void executeLine(int index){
		program.get(index).execute(context, pc);
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		int counter = 0;
		for(Instruction i : program){
			builder.append(counter + " " + i.print() + "\n");
			counter++;
		}
		return builder.toString();
	}
	
	/**
	 * To execute instructions, all Instruction objects will need access to the same Memory Object 
	 * and the same PC from the computer. References to these are attributes that are set at load() 
	 * by the Computer object.
	 * @param context = the memory of the computer
	 * @param pc = the program counter in the computer
	 */
	public void setContext(Memory context, PC pc){
		this.context = context;
		this.pc = pc;
	}

}
