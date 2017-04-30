package gui;

import java.util.EventObject;

public class SubmitEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;
	
	private String currentSlot;
	private String content;

	public SubmitEvent(Object source, String currentSlot, String content) {
		super(source);
		this.currentSlot = currentSlot;
		this.content = content;
	}

	public String getCurrentSlot(){
		return currentSlot;
	}
	
	public String getContent(){
		return content;
	}
	

}
