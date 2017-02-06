package ex2_2;

import java.lang.reflect.Type;

public class SEK extends CountryCurrency {
	
	public static final double exRate = 8.76;

	@Override
	public int compareTo(Currency o) {
		// TODO Auto-generated method stub
		return Double.compare(this.exRate, o.getExRate());
	}

	@Override
	public double getExRate() {
		return SEK.exRate;
	}
	
	public String toString(double amount){
		double money = this.exRate*amount;
		StringBuilder howMuch = new StringBuilder();
		howMuch.append(money);
		return howMuch.toString();
	}

}
