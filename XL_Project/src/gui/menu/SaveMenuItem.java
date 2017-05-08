package gui.menu;

import gui.StatusLabel;
import gui.SubmitEvent;
import gui.SubmitListener;
import gui.XL;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.event.EventListenerList;

public class SaveMenuItem extends OpenMenuItem {
    
	private static final long serialVersionUID = 1L;
	
	private EventListenerList listenerList = new EventListenerList();

	public SaveMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Save");
    }

    protected void action(String path) throws FileNotFoundException {
        fireSubmitEvent(new SubmitEvent(this,path,path));
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showSaveDialog(xl);
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
}