package gui;

import java.awt.event.KeyListener;
import java.util.EventListener;

public interface SubmitListener extends EventListener {
	
	public void submitEventOccured(SubmitEvent submit);

}
