package computer.software;

import java.util.ArrayList;

import computer.PC;
import computer.hardware.Memory;
import computer.hardware.Word;

public abstract class Program {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Memory context;
	private ArrayList<Instruction> program;
	private PC pc;
	
	public void executeLine(int index){
		program.get(index).execute();
	}
	
	public void print(){
		for(Instruction i : program){
			System.out.println(i.print());
		}
	}
	
	public void setContext(Memory context, PC pc){
		this.context = context;
		this.pc = pc;
	}
	
	protected interface Instruction{	
		public void execute();	
		public String print();
	}
	
	protected class Address{
		private int address;
		public Address(int address){
			this.address = address;
		}
		protected Word read(){
			return context.get(address);
		}
		protected boolean write(Word word){
			return context.add(word);
		}
		protected String prt(){
			return "[" + address + "]";
		}
	}
	
	protected class ADD implements Instruction{
		
		private Word w1;
		private Word w2;
		private Address address;
		
		public ADD(Word w1, Word w2, Address address){
			this.w1 = w1;
			this.w2 = w2;
			this.address = address;
		}

		@Override
		public void execute() {
			address.write(w1.add(w2));
			pc.setPC(pc.getPC() + 1);
		}
		
		public String print(){
			return "ADD " + w1.prt() + " " + w2.prt() + " " + address.prt();
		}
		
	}

}
