package gui;

import java.util.EventListener;

public interface ExceptionListener extends EventListener {
	
	public void exceptionEventOccured(ExceptionEvent event);

}
