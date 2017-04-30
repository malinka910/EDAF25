package gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JTextField;

public class Editor extends JTextField implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	private String currentSlot;
	
    public Editor() {
        setBackground(Color.WHITE);
        currentSlot = "A1";
        addKeyListener(this);
    }
    
    public void fireSubmitEvent(SubmitEvent submit){
    	Object[] listeners = listenerList.getListenerList();
		for(Object l : listeners){
			if(l instanceof SubmitListener){
				((SubmitListener) l).submitEventOccured(submit);
			}
		}
    }
    
    protected void setCurrentSlot(String currentSlot){
    	this.currentSlot = currentSlot;
    }

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("SOME KEY PRESSED");
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			System.out.println("ENTER KEY");
			System.out.println("'" + this.getText() + "' into: " + currentSlot);
			//TODO: Make sure this connects to a spreadsheet at some point
			//fireSubmitEvent(new SubmitEvent(this, currentSlot, this.getText()));
		}
	}
	
	//------------------------------------------
	// Do-Nothing methods below here
	//------------------------------------------
	
	@Override
	public void keyTyped(KeyEvent e) {
		// DO NOTHING
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// DO NOTHING
	}
    
}