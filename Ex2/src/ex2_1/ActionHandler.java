package ex2_1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

public class ActionHandler implements ActionListener {
private Application application;
private List<ButtonThing> buttonList ;

	private ActionHandler(){
		super();
		for(int i = 0 ; i < 3 ; i++){
		buttonList.add(new ButtonThing(application.getAction(i)));
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//Have to assume that ButtonThing objects are used
		((ButtonThing) e.getSource()).action();
	}

}
