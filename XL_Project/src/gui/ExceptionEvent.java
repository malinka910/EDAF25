package gui;

import java.util.EventObject;

public class ExceptionEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;
	
	private String message;

	public ExceptionEvent(Object source, String message) {
		super(source);
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}

	

}
