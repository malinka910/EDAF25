package computer.hardware;

import computer.software.Operand;

public class Address implements Operand {
	
	private int address;
	
	public Address(int address){
		this.address = address;
	}

	@Override
	public Word getWord(Memory context) {
		return context.get(address);
	}

	@Override
	public String prt() {
		return "[" + address + "]";
	}

}
