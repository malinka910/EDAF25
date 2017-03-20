package computer.hardware;

public interface Word {
	
	public Word add(Word w1);
	
	public boolean eql(Word w1);
	
	public Word mul(Word w1);
	
	public Word cpy();
	
	public String prt();
	
	public long getValue();

}
