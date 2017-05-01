package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import data.Spreadsheet;

import javax.swing.event.EventListenerList;

public class SlotLabel extends ColoredLabel implements Observer, MouseListener {
	
	private static final long serialVersionUID = 1L;
	private String labelName;
	private String content;
	private EventListenerList listenerList = new EventListenerList();
	
	/** The SlotLabel is instantiated with it's name as a parameter and will update its content when it 
	 * observes that the model has changed. It will fire a SelectEvent to any SelectListeners that have 
	 * registered with it when the user click on the SlotLable. */
    public SlotLabel(String labelName) {
        super("                    ", Color.WHITE, RIGHT);
        this.labelName = labelName;
        addMouseListener(this);
    }

    /** When a SlotLabel observes a change in the model, it will update its content and display an updated 
     * value to the user. */
	@Override
	public void update(Observable o, Object arg) { 
		content = ((Spreadsheet)o).content(labelName);
		if(content == null){
			this.setText("");
		}else{
			if(content.startsWith("#")){
				this.setText(content.substring(1));
				System.out.println(content);
			}else{
				//TODO: This is unnecessary... The data won't be in the model if it's not acceptable content.
				try{
					StringBuilder builder = new StringBuilder();
					builder.append(((Spreadsheet)o).value(labelName));
					this.setText(builder.toString());
				}catch(Exception e){
					//System.out.println("SlotLabel Exception: " + e.getMessage());
					this.setText("*"); //TODO: can steamline this, the model won't update unless everything is alright.
				}
			}
		}
	}
	
	/** Fire a SelectEvent object to all SelectListeners that are registered to it. */
	public void fireSelectEvent(SelectEvent select){
		Object[] listeners = listenerList.getListenerList();
		for(Object l : listeners){
			if(l instanceof SelectListener){
				((SelectListener) l).selectEventOccured(select);
			}
		}
	}
	
	/** Add/Register a SelectListener. */
	public void addSelectListener(SelectListener listener){
		listenerList.add(SelectListener.class, listener);
	}
	
	/** Remove/Unregister a SelectListener. */
	public void removeSelectListener(SelectListener listener){
		listenerList.remove(SelectListener.class, listener);
	}

	/** Once a SlotLabel hears that it has been clicked (selected by the user), it will fire a SelectEvent. */
	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("SlotLabel Clicked: " + labelName);
		fireSelectEvent(new SelectEvent(this, labelName, content));
	}

	//------------------------------------------
	// Do-Nothing methods below here
	//------------------------------------------
	
	@Override
	public void mousePressed(MouseEvent e) {
		// Do Nothing
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Do Nothing		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Do Nothing		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Do Nothing		
	}
}