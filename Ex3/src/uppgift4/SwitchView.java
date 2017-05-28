package uppgift4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public class SwitchView extends JButton implements Observer {
	
	private int action;

	public SwitchView(){
		super("off");
		action = 0;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		String state = (String) arg;
		setLabel(state);
	}

	

}
