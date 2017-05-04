package gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import controller.ExceptionEvent;
import controller.ExceptionListener;
import controller.SelectEvent;
import controller.SelectListener;

public class StatusLabel extends ColoredLabel implements Observer, ExceptionListener, SelectListener {

	private static final long serialVersionUID = 1L;

	/** The StatusLabel is meant to display error messages to the user. It will listen for ExceptionEvents and 
	 * then display the exception's error message. It will also clear a previous error message when it hears that 
	 * the user has selected a new 'current slot' or is it observes that something was successfully inserted into the 
	 * model (Spreadsheet). */
	public StatusLabel() {
        super("", Color.WHITE);
    }

	/** When the StatusLabel hears that an Exception has been thrown, it will display the exception's 
	 * error message. */
	@Override
	public void exceptionEventOccured(ExceptionEvent event) {
		System.out.println("StatusLabel ExceptionEvent");
		String message = event.getMessage();
		System.out.println("ExceptionEvent: " + message);
		this.setText(message);
	}

	/** When the StatusLable hears that a SlotLable has been selected by the user, it will clear any error 
	 * message. */
	@Override
	public void selectEventOccured(SelectEvent event) {
		this.setText("");	
	}
	
	/** When the StatusLabel observes that the model has changed, it clears any error message. */
	@Override
	public void update(Observable o, Object arg) {
		this.setText("");
	}
}