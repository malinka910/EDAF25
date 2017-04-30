package gui;

import java.util.EventObject;

public class SelectEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	
	private String labelName;
	
	public SelectEvent(Object source, String labelName) {
		super(source);
		this.labelName = labelName;
	}

	public String getLabelName(){
		return labelName;
	}
	
}
