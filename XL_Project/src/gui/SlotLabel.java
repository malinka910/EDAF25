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
	//double value;
	private EventListenerList listenerList = new EventListenerList();
	
    public SlotLabel(String labelName) {
        super("                    ", Color.WHITE, RIGHT);
        this.labelName = labelName;
        addMouseListener(this);
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO this will be an observer of the spreadsheet class when we get that far
		// TODO we're gonna have to revisit this to implement clear functions. 
		String content = ((Spreadsheet)o).content(labelName);
		
		if(content == null){
			this.setText("");
		}else{
			if(content.startsWith("#")){
				this.setText(content);
				System.out.println(content);
			}else{
				StringBuilder builder = new StringBuilder();
				builder.append(((Spreadsheet)o).value(labelName));
				this.setText(builder.toString());
			}
		}
	}
	
	public void fireSelectEvent(SelectEvent select){
		Object[] listeners = listenerList.getListenerList();
		for(Object l : listeners){
			if(l instanceof SelectListener){
				((SelectListener) l).selectEventOccured(select);
			}
		}
	}
	
	public void addSelectListener(SelectListener listener){
		listenerList.add(SelectListener.class, listener);
	}
	
	public void removeSelectListener(SelectListener listener){
		listenerList.remove(SelectListener.class, listener);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("SlotLabel Clicked: " + labelName);
		fireSelectEvent(new SelectEvent(this, labelName));
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