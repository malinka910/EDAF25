package uppgift4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Switch extends JButton implements ActionListener {

	private boolean on = false; 		//data model
	
	public Switch(){
		super("OFF"); 					//view
		addActionListener(this); 		//controller 
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {	//controller -> model & view
		
		on = !on;						//model
		setLabel(on ? "ON" : "OFF");	//view
	}
	
	

}
