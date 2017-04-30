package gui;

import java.awt.Color;

import javax.swing.event.EventListenerList;

public class CurrentLabel extends ColoredLabel {
	
	private static final long serialVersionUID = 1L;
	EventListenerList listenerList = new EventListenerList();
	
    public CurrentLabel() {
        super("A1", Color.WHITE);
    }
    
    public void addSubmitListener(SubmitListener listener){
    	listenerList.add(SubmitListener.class, listener);
    }
    
    public void removeSubmitListener(SubmitListener listener){
    	listenerList.remove(SubmitListener.class, listener);
    }

}