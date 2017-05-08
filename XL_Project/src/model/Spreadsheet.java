package model;


import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import expr.Environment;
import util.XLException;

/**
 * The data structure of for the XL program. It stores the spreadsheet data as an address-content mapping 
 * using a Hashmap<String,Slot>. It is also an observable that will update all of its observers when there is 
 * a state change. It listens to components in the GUI for SubmitEvents and fires ExceptionEvents when exceptions 
 * are thrown internally.
 */
public class Spreadsheet extends Observable implements Environment, Observer {
	
	HashMap<String,Slot> sheet;
	
	/** Constructor for blank spreadsheet. */
	public Spreadsheet(){
		sheet = new HashMap<String,Slot>();
	}

	//----------------------------------------------------------------------------
	// Strategy to check the integrity of the data before changes are finalized 
	//----------------------------------------------------------------------------
	/**
	 * Try to calculate the value for every filled slot in the spreadsheet.
	 * @return true if the all values can be calculated without throwing an exception.
	 */
	private boolean integrityCheckPassed(){
		try{
			for(String s : sheet.keySet()){
				Slot oldSlot = sheet.get(s);
				sheet.put(s, new DummySlot());
				oldSlot.value(this);
				sheet.put(s, oldSlot);
			}
			return true;
		}catch(Exception e){
			setChanged();
			notifyObservers(e);
			return false;
		}
	}
	
	//----------------------------------------------------------------------------
	// Methods for extracting specific data (used by observers and internally) 
	//----------------------------------------------------------------------------
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

	/** Evaluate the expression stored in the slot 'name'. */
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
	
	@Override
	public void update(Observable o, Object arg) {
		
		String input = arg.toString();
		String instruction = input.substring(0, input.indexOf("="));
		String where = input.substring(input.indexOf("=")+1);

		switch(instruction){
		
			case "save":
				save(where); 
				break;
				
			case "load":
				load(where);
				break;
				
			case "clear": 
				clear(where); 
				break;
				
			case "clearAll": 
				clearAll();
				break;
				
			default: 
				insert(instruction , where);
				
		}
	}
	
	/**
	 * A method to insert content into a slot in the spreadsheet.
	 * @param slotName to be inserted into.
	 * @param content to be inserted into the slot.
	 * @return true if the insertion was sucessful. 
	 */
	private void insert(String slotName, String content){
		SlotFactory factory = new SlotFactory();
		Slot oldSlot = sheet.get(slotName);
		sheet.put(slotName, factory.build(content));
		if(integrityCheckPassed()){
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
	 * Clear a specific key-value mapping and notify the Observers.
	 * @param name of the slot to be cleared.
	 */
	private void clear(String name){
		if(sheet.containsKey(name)){
			Slot oldSlot = sheet.get(name);
			sheet.remove(name);
			if(integrityCheckPassed()){
				this.setChanged();
				this.notifyObservers();
			}else{
				sheet.put(name, oldSlot);
			}
		}
	}
	
	/** Clear All key-values in the HashMap and notify the Observers. */
	private void clearAll(){
		sheet.clear();
		this.setChanged();
		this.notifyObservers(null);
	}
	
	/** Save the current spreadsheet to the given file. */
	private void save(String file){
		try{
			XLPrintStream stream = new XLPrintStream(file);
			stream.save(sheet.entrySet());
			stream.close();
			this.setChanged();
			this.notifyObservers(null);
		}catch(Exception e){
			setChanged();
			notifyObservers(e);
		}
	}
	
	/** Load a saved spreadsheet from the given file. */
	private void load(String file){
		try{
			clearAll();
			XLBufferedReader reader = new XLBufferedReader(file);
			reader.load(sheet);
			reader.close();
			this.setChanged();
			this.notifyObservers(null);
		}catch(Exception e){
			setChanged();
			notifyObservers(e);
		}
	}
	
}
