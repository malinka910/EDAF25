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
	
	public Program(){
		program = new ArrayList<Instruction>();
	}
	
	public void add(Instruction instruction){
		program.add(instruction);
	}
	
	public void executeLine(int index){
		program.get(index).execute();
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
	
	public void setContext(Memory context, PC pc){
		this.context = context;
		this.pc = pc;
	}
	
	//-----------------------------------
	// Address Class
	//-----------------------------------
	
	class Address implements Word {
		private int address;
		public Address(int address){
			this.address = address;
		}
		protected Word read(){
			return context.get(address);
		}
		protected void write(Word word){
			context.set(address , word);
		}
		@Override
		public String prt(){
			return "[" + address + "]";
		}
		public String prtWord(){
			return context.get(address).prt();
		}
		@Override
		public Word add(Word w1) {
			return context.get(address).add(w1);
		}
		@Override
		public boolean eql(Word w1) {
			return context.get(address).eql(w1);
		}
		@Override
		public Word mul(Word w1) {
			return context.get(address).mul(w1);
		}
		@Override
		public Word cpy() {
			return context.get(address).cpy();
		}
		@Override
		public long getValue() {
			return context.get(address).getValue();
		}
	}
	
	//---------------------------------------------------------
	// Program Instructions (Command Pattern)
	//---------------------------------------------------------
	
	/**
	 * Instruction interface: creates the enforces a command method implementation execute()
	 * TODO: ADD MUL JEQ CPY PRT JMP HLT
	 */
	private interface Instruction{	
		public void execute();	
		public String print();
	}
	
	/**
	 * ADD Instruction, take two objects that implement the Word interface, add the values, store sum at given address.
	 */
	protected class Add implements Instruction{
		
		private Word w1;
		private Word w2;
		private Address address;
		
		public Add(Word w1, Word w2, Address address){
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
	
	/**
	 * MUL Instruction
	 */
	protected class Mul implements Instruction {
		
		private Word w1;
		private Word w2;
		private Address address;
		
		public Mul(Word w1, Word w2, Address address){
			this.w1 = w1;
			this.w2 = w2;
			this.address = address;
		}

		@Override
		public void execute() {
			address.write(w1.mul(w2));
			pc.setPC(pc.getPC() + 1);
		}

		@Override
		public String print() {
			return "MUL " + w1.prt() + " " + w2.prt() + " " + address.prt();
		}
		
	}
	
	/**
	 * JEQ Instruction
	 */
	protected class JumpEq implements Instruction {
		
		private long jumpTo;
		private Word w1;
		private Word w2;

		public JumpEq(long jumpTo, Word w1, Word w2) {
			this.jumpTo = jumpTo;
			this.w1 = w1;
			this.w2 = w2;
		}
		
		@Override
		public void execute() {
			if(w1.eql(w2)){
				pc.setPC((int)jumpTo);
			}else{
				pc.setPC(pc.getPC()+1);
			}
		}

		@Override
		public String print() {
			return "JEQ " + jumpTo + " " + w1.prt() + " " + w2.prt();
		}

	}
	
	protected class Print implements Instruction {
		
		private Word w;
		
		public Print(Word w){
			this.w = w;
		}

		@Override
		public void execute() {
			pc.setPC(pc.getPC() + 1);
			if(w instanceof Address){
				System.out.println(((Address) w).prtWord());
			}else{
				System.out.println(w.prt());
			}
		}

		@Override
		public String print() {
			return "PRT " + w.prt();
		}
		
	}
	
	protected class Halt implements Instruction {
		
		public Halt(){
			// anin't got no operands yo
		}

		@Override
		public void execute() {
			pc.setPC(-1);
		}

		@Override
		public String print() {
			return "HLT";
		}
		
	}
	
	protected class Jump implements Instruction {
		
		private long jumpTo;

		public Jump(long jumpTo) {
			this.jumpTo = jumpTo;
		}
		
		@Override
		public void execute() {
			pc.setPC((int)jumpTo);
		}

		@Override
		public String print() {
			return "JMP " + jumpTo;
		}

		
		
	}
	
	protected class Copy implements Instruction {
		
		private Word w1;
		private Address address;
		
		public Copy(Word w1, Address address){
			this.w1 = w1;
			this.address = address;
		}

		@Override
		public void execute() {
			address.write(w1);
			pc.setPC(pc.getPC()+1);
		}

		@Override
		public String print() {
			return "PRT " + w1.prt() + " " + address.prt();
		}
		
	}

}
