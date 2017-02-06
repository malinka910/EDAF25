package ex2_2;

class Amount<T extends CountryCurrency> {
	//T extends Comparable<T>
	private T t;
	private double amount;
	
	public Amount(double amount1){
		this.amount = amount/t.getExRate();
	}
	
	public void add(Amount<T> money) throws ClassCastException {
		 this.amount += money.amount;
	}
	
	public void sub(Amount<T> money) throws ClassCastException {
		 this.amount -= money.amount;
	}
	
	public String toString(){
		return t.toString(this.amount);
	}
	
	public Amount<?> convert(T ex){
		return new Amount<T>(this.amount);
	}
	
	
	

}
