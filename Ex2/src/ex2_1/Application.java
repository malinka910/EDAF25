package ex2_1;

import java.util.List;

public class Application {
	
	private List<Action> list;
	
	public Application(){
		list.add(new Action1());
		list.add(new Action2());
		list.add(new Action3());
	}
	
	
	private class Action1 implements Action{
		@Override
		public void action() {
			
		}	
	}
	
	private class Action2 implements Action{
		@Override
		public void action() {
			
		}	
	}
	
	private class Action3 implements Action{
		@Override
		public void action() {
			
		}	
	}
	
	public Action getAction(int i){
		return list.get(i);
	}
	
}
