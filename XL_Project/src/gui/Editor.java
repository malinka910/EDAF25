package gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;
import javax.swing.event.EventListenerList;

import model.Spreadsheet;

public class Editor extends JTextField implements KeyListener, SelectListener, Observer {
	
	private static final long serialVersionUID = 1L;
	private EventListenerList listenerList = new EventListenerList();
	private String currentSlot;
	private StatusLabel statusLabel;
	
	/** Editor instantiated with default slot 'A1'. 
	 * Editor objects listen to SlotLabels for SelectEvents and to themselves for KeyEvents. */
    public Editor(StatusLabel statusLabel) {
        setBackground(Color.WHITE);
        currentSlot = "A1";
        addKeyListener(this);
        this.statusLabel = statusLabel;
    }
    
    /** The Editor keeps track of the currentSlot by listening for a SlotLabel to fire a SelectEvent. 
     * It will also display the current content of the slot if any. (i.e. content = comment or expression String) */
    @Override
	public void selectEventOccured(SelectEvent event) {
		this.currentSlot = event.getLabelName();
		this.setText(event.getLabelContent());
	}

    /** When the Enter key is pressed, the Editor will check the syntax of the text that the user has 
     * input to the textField. If the syntax of the text is correct, it will fire a SubmitEvent with the text 
     * to all SubmitListeners that have registered with it. If the text syntax is not correct, an ExceptionEvent 
     * with the error text will be fired to all ExceptionListeners registered with the Editor. */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			String text = this.getText();
			fireSubmitEvent(new SubmitEvent(this, currentSlot, text, statusLabel));
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg == null){
			Spreadsheet sheet = (Spreadsheet) o;
			this.setText(sheet.content(currentSlot));
		}
	}
	
	//---------------------------------------------------------------
	// add/remove SubmitListeners and fire SubmitEvent
	//---------------------------------------------------------------
	
	/** Add a SubmitListener to the eventListenerList */
	public void addSubmitListener(SubmitListener listener){
		listenerList.add(SubmitListener.class, listener);
	}
	
	/** Remove a SubmitListener from the eventListenerList */
	public void removeSubmitListener(SubmitListener listener){
		listenerList.remove(SubmitListener.class, listener);
	}
	
	/** Call submitEventOccured() on every SubmitListener in the eventListenerList */
	public void fireSubmitEvent(SubmitEvent submit){
    	Object[] listeners = listenerList.getListenerList();
		for(Object l : listeners){
			if(l instanceof SubmitListener){
				((SubmitListener) l).submitEventOccured(submit);
			}
		}
    }
	
	//---------------------------------------------------------------
	// add/remove ExceptionListeners and fire ExceptionEvent
	//---------------------------------------------------------------
	
	/** Add an ExceptionListener to the eventListenerList */
	public void addExceptionListener(ExceptionListener listener){
		listenerList.add(ExceptionListener.class, listener);
	}
	
	/** Remove an ExceptionListener from the eventListenerList */
	public void removeExceptionListener(ExceptionListener listener){
		listenerList.remove(ExceptionListener.class, listener);
	}
	
	/** Call exceptionEventOccured() on every ExceptionListener in the eventListenerList */
	public void fireExceptionEvent(ExceptionEvent event){
		Object[] listeners = listenerList.getListenerList();
		for(Object l : listeners){
			if(l instanceof ExceptionListener){
				((ExceptionListener) l).exceptionEventOccured(event);;
			}
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