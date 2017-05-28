package computer.hardware;

import computer.software.Operand;

public interface Word extends Operand {
	
	public Word add(Word w1);
	
	public boolean eql(Word w1);
	
	public Word mul(Word w1);
	
	public Word cpy();
	
	public String prt();
	
	public long getValue();
	
	public void setValue(Word newValue);

}
