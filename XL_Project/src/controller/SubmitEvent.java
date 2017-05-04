package controller;

import java.util.EventObject;

import gui.menu.LoadMenuItem;
import gui.menu.SaveMenuItem;

public class SubmitEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;
	
	private String currentSlot;
	private String content;
	private boolean load;
	private boolean save;

	public SubmitEvent(Object source, String currentSlot, String content) {
		super(source);
		this.currentSlot = currentSlot;
		this.content = content;
		load = false;
		save = false;
		if(source instanceof SaveMenuItem){
			save = true;
		}else if(source instanceof LoadMenuItem){
			load = true;
		}
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

}
