package ex2_1;

import javax.swing.JButton;

public class ButtonThing extends JButton {
	
	Action action;
	
	public ButtonThing(Action action){
		this.action = action;
	}
	
	protected void action(){
		action.action();
	}

}
