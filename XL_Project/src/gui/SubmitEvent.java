package gui;

import java.util.EventObject;

import javax.swing.event.EventListenerList;

import expr.ExprParser;
import gui.menu.LoadMenuItem;
import gui.menu.SaveMenuItem;

public class SubmitEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;
	
	private EventListenerList listenerList = new EventListenerList();
	
	private String currentSlot;
	private String content;
	private boolean load;
	private boolean save;
	private boolean badSyntax;

	public SubmitEvent(Object source, String currentSlot, String content) {
		super(source);
		this.currentSlot = currentSlot;
		this.content = content;
		load = false;
		save = false;
		badSyntax = false;
		if(source instanceof SaveMenuItem){
			save = true;
		}else if(source instanceof LoadMenuItem){
			load = true;
		}
	}
	
	public SubmitEvent(Object source, String currentSlot, String content, ExceptionListener statusLabel) {
		super(source);
		this.currentSlot = currentSlot;
		this.content = content;
		load = false;
		save = false;
		listenerList.add(ExceptionListener.class, statusLabel);
		if(source instanceof SaveMenuItem){
			save = true;
		}else if(source instanceof LoadMenuItem){
			load = true;
		}
		checkInput(content);
	}

	public String getCurrentSlot(){
		return currentSlot;
	}
	
	public String getContent(){
		return content;
	}
	
	public boolean isSave(){
		return save;
	}
	
	public boolean isLoad(){
		return load;
	}
	
	public boolean badSyntax(){
		return badSyntax;
	}
	
	//---------------------------------------------------------------
		// add/remove ExceptionListeners and fire ExceptionEvent
		//---------------------------------------------------------------
		
		/** Add an ExceptionListener to the eventListenerList */
		public void addExceptionListener(ExceptionListener listener){
			listenerList.add(ExceptionListener.class, listener);
		}
		
		/** Remove an ExceptionListener from the eventListenerList */
		public void removeExceptionListener(ExceptionListener listener){
			listenerList.remove(ExceptionListener.class, listener);
		}
		
		/** Call exceptionEventOccured() on every ExceptionListener in the eventListenerList */
		public void fireExceptionEvent(ExceptionEvent event){
			Object[] listeners = listenerList.getListenerList();
			for(Object l : listeners){
				if(l instanceof ExceptionListener){
					((ExceptionListener) l).exceptionEventOccured(event);;
				}
			}
		}
		
		private void checkInput(String text){
		if(text.startsWith("#")){
			badSyntax = false;
		}else{
			ExprParser parser = new ExprParser();
			try{
				parser.build(text);
				badSyntax = false;
			}catch(Exception ex){
				badSyntax = true;
				fireExceptionEvent(new ExceptionEvent(this, ex.getMessage()));
			}
		}
		}

}
