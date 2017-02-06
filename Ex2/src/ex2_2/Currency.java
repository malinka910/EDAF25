package ex2_2;

import java.lang.reflect.Type;

public interface Currency extends Comparable<Currency> {
	
	public double getExRate();
	
	public String toString(double amount);

}
