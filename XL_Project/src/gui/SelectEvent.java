package gui;

import java.util.EventObject;

public class SelectEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	
	private String labelName;
	private String labelContent;
	
	/** A select event is created when the user clicks on a SlotLable object. The SelectEvent is then fired from 
	 *  that SlotLable to Objects that implement the SelectListener interface. The listeners will be able to extract 
	 *  which SlotLable was selected and the content (expression or comment String) that the selected slot contains. */
	public SelectEvent(Object source, String labelName, String labelContent) {
		super(source);
		this.labelName = labelName;
		this.labelContent = labelContent;
	}

	/** Get the name of the selected slot (e.g. 'A1',...,'B4',...,'H8', etc.) */
	public String getLabelName(){
		return labelName;
	}
	
	/** Get the content of the selected slot. */
	public String getLabelContent(){
		return labelContent;
	}
	
}
