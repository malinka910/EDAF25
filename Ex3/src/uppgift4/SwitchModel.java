package uppgift4;

import java.util.*;


/* Subject Class (model in the design pattern) */
public class SwitchModel extends Observable {

	private List<Observer> observers = new ArrayList<Observer>();
	private boolean on;
	private int changeState;
	
	public SwitchModel(){
		on = false;
	}
	
	public void changeState(){
		on = !on;
	}
	
	public String state(){
		System.out.println(String.valueOf(on));
		return "";
	}
	
	public void attach(Observer observer){
		observers.add(observer);
	}
	
	//TODO change null to something useful
	public void notifyAllObservers(){
		for(Observer observer : observers){
			observer.update(null, state());
		}
	}
	
}
