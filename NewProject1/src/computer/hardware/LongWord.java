package computer.hardware;

public class LongWord implements Word {
	
	private long value;
	
	public LongWord(long value){
		this.value = value;
	}
	
	@Override
	public Word add(Word w1) {
		return new LongWord(((LongWord)w1).getValue() + this.value);
	}

	@Override
	public boolean eql(Word w1) {
		return this.value == ((LongWord)w1).getValue();
	}

	@Override
	public Word mul(Word w1) {
		//return new LongWord(((LongWord)w1).getValue() * this.value);
		return new LongWord(w1.getValue() * this.value);
	}

	@Override
	public Word cpy() {
		return new LongWord(value);
	}

	@Override
	public String prt() {
		StringBuilder builder = new StringBuilder();
		builder.append(value);
		return builder.toString();
	}

	@Override
	public long getValue() {
		return value;
	}

	@Override
	public void setValue(Word newValue) {
		value = newValue.getValue();
	}

	@Override
	public Word getWord(Memory context) {
		return this;
	}

}
