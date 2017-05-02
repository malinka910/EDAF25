package data;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;

import java.util.Set;

import javax.swing.event.EventListenerList;

import com.sun.javafx.collections.MappingChange.Map;

import expr.Environment;
import gui.ExceptionEvent;
import gui.ExceptionListener;
import gui.SubmitEvent;
import gui.SubmitListener;
import util.XLException;
/**
 * The data structure of for the XL program. It stores the spreadsheet data as an address-content mapping 
 * using a Hashmap<String,Slot>. It is also an observable that will update all of its observers when data 
 * is inserted or removed from the spreadsheet.
 * @author Greg
 *
 */
public class Spreadsheet extends Observable implements Environment, SubmitListener {
	
	HashMap<String,Slot> sheet;
	private EventListenerList listenerList = new EventListenerList();
	
	/** Constructor for blank spreadsheet. */
	public Spreadsheet(){
		sheet = new HashMap<String,Slot>();
	}
	
	/** Constructor for a given  */
	public Spreadsheet(HashMap<String,Slot> sheet){
		this.sheet = new HashMap<String,Slot>();
	}
	
	public void addExceptionListener(ExceptionListener listener){
		listenerList.add(ExceptionListener.class, listener);
	}
	
	public void removeExceptionListener(ExceptionListener listener){
		listenerList.remove(ExceptionListener.class, listener);
	}
	
	public void fireExceptionEvent(ExceptionEvent event){
		Object[] listeners = listenerList.getListenerList();
		for(Object l : listeners){
			if(l instanceof ExceptionListener){
				((ExceptionListener) l).exceptionEventOccured(event);;
			}
		}
	}
	
	/**
	 * A method to insert content into a slot in the spreadsheet.
	 * @param slotName to be inserted into.
	 * @param content to be inserted into the slot.
	 * @return true if the insertion was sucessful. 
	 */
	public void insert(String slotName, String content){
		SlotFactory factory = new SlotFactory();
		Slot oldSlot = sheet.get(slotName);
		sheet.put(slotName, factory.build(content));
		if(changeCheckPassed()){
			this.setChanged();
			this.notifyObservers();
		}else{
			if(oldSlot == null){
				sheet.remove(slotName);
			}else{
				sheet.put(slotName, oldSlot);
			}
		}
	}
	
	/**
	 * Try to calculate the value for every filled slot in the spreadsheet.
	 * @return true if the all values can be calculated without throwing an exception.
	 */
	private boolean changeCheckPassed(){
		try{
			for(String s : sheet.keySet()){
				Slot oldSlot = sheet.get(s);
				sheet.put(s, new DummySlot());
				oldSlot.value(this);
				sheet.put(s, oldSlot);
			}
			return true;
		}catch(Exception e){
			fireExceptionEvent(new ExceptionEvent(this, e.getMessage()));
			return false;
		}
	}
	
	/**
	 * Get the string value of a given slot. Both Expressions and Comments will be returned.
	 * @param name of the desired slot
	 * @return the String that the slot object contains. 
	 */
	public String content(String name){
		if(sheet.containsKey(name)){
			return sheet.get(name).getContent();
		}else{
			return null;
		}
	}

	/**
	 * Evaluate the expression stored in the slot 'name'.
	 */
	@Override
	public double value(String name) {
		if(sheet.containsKey(name)){
			try{
				return sheet.get(name).value(this);
			}catch(Exception e){
				throw new XLException(e.getMessage());
			}
		}else{
			throw new XLException("Reference to Empty Slot");
		}
	}
	
	/**
	 * Clear a specific key-value mapping and notify the Observers.
	 * @param name of the slot to be cleared.
	 */
	public void clear(String name){
		System.out.println("ClearMethodCalled" + name);
		if(sheet.containsKey(name)){
			System.out.println("SheetContainsName");
			Slot oldSlot = sheet.get(name);
			sheet.remove(name);
			if(changeCheckPassed()){
				System.out.println("ChangeCeckPassed");
				this.setChanged();
				this.notifyObservers();
				System.out.println("ObserversNotified");
			}else{
				System.out.println("ChangeCeck NOT Passed");
				sheet.put(name, oldSlot);
			}
		}
	}
	
	/**
	 * Clear All key-values in the HashMap and notify the Observers.
	 */
	public void clearAll(){
		sheet.clear();
		this.setChanged();
		this.notifyObservers();
	}
	
	/** Quick and dirty way to get all info for a save file
	 * getSlotSet() returns a set of strings that are the key-value mappings for the slots in 
	 * the HashMap */
	public Set<String> getSlotSet(){
		Set<String> slotSet = new HashSet<String>();
		for(String key : sheet.keySet()){
			StringBuilder builder = new StringBuilder();
			builder.append(key.toLowerCase());
			builder.append("=");
			builder.append(sheet.get(key).getContent());
			slotSet.add(builder.toString());
		}
		return slotSet;
	}

	@Override
	public void submitEventOccured(SubmitEvent submit) {
		if(submit.getContent() == null){
			clear(submit.getCurrentSlot()); // SubmitEvent from ClearMenuItem
		}else if(submit.getCurrentSlot()==null){
			clearAll(); // SubmitEvent from ClearAllMenuItem
		}else{
			insert(submit.getCurrentSlot(), submit.getContent()); // SubmitEvent from Editor
		}
			printToConsole(); //For testing
	}
	
	private void printToConsole(){
		for(String s : sheet.keySet()){
			System.out.println(s + ":" + sheet.get(s).getContent() + ":" + value(s));
		}
	}

}
