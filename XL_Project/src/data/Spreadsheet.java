package data;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import expr.Environment;
import expr.Expr;
import expr.ExprParser;
/**
 * The data structure of for the XL program. It stores the spreadsheet data as an address-content mapping 
 * using a Hashmap<String,Slot>. It is also an observable that will update all of its observers when data 
 * is inserted or removed from the spreadsheet.
 * TODO: the strategy for loop checking is unimplemented as of yet.
 * @author Greg
 *
 */
public class Spreadsheet extends Observable implements Environment {
	
	HashMap<String,Slot> sheet;
	
	/**
	 * Constructor for blank spreadsheet.
	 */
	public Spreadsheet(){
		sheet = new HashMap<String,Slot>();
	}
	
	/**
	 * A method to insert content into a slot in the spreadsheet.
	 * @param slotName to be inserted into.
	 * @param content to be inserted into the slot.
	 * @return true if the insertion was sucessful. 
	 */
	public boolean instert(String slotName, String content){
		boolean changed = false;
		if(sheet.containsKey(slotName)){
			// Check if comment or expression
			if(sheet.get(slotName) instanceof ExpressionSlot){
				// If comment: check for loops
				if(loopChecker(slotName, content)){
					// If no loops: put slot into the HashMap
					sheet.put(slotName, new ExpressionSlot(content));
					//System.out.println("Put old expression: True");
					this.setChanged();
					this.notifyObservers();
					changed = true;
				}else{
					// If loops: don't put slot into HashMap and throw exception
					// TODO THROW XLException
					//System.out.println("Put old expression: False");
					changed = false;
				}
			}else{
				// If not an expression: Just add the comment to the HashMap
				sheet.put(slotName, new CommentSlot(content));
				//System.out.println("Put old not-expression: True");
				this.setChanged();
				this.notifyObservers();
				changed = true;
			}
		}else{
			if(content.startsWith("#")){
				sheet.put(slotName, new CommentSlot(content));
				//System.out.println("Put new comment: True");
				this.setChanged();
				this.notifyObservers();
				changed = true;
			}else{
				sheet.put(slotName, new ExpressionSlot(content));
				//System.out.println("Put new expression: True");
				this.setChanged();
				this.notifyObservers();
				changed = true;
			}
		}
		this.clearChanged();
		return changed;
	}
	
	/**
	 * This method will check the current spreadsheet for self references in a new expression before 
	 * the expression is inserted into the spreadsheet.
	 * @param slotName of the slot to be changed
	 * @param content that will be checked for self referential loops
	 * @return true if no loop was found. 
	 */
	private boolean loopChecker(String slotName, String content){
		boolean loopNotFound = true;
		//TODO Write code to test if there is a self reference
		return loopNotFound;
	}
	
	/**
	 * Get the string value of a given slot. Both Expressions and Comments will be returned.
	 * @param name of the desired slot
	 * @return the String that the slot object contains. 
	 */
	public String content(String name){
		if(sheet.containsKey(name)){
			return sheet.get(name).toString();
		}else{
			return null;
		}
	}

	/**
	 * Evaluate the expression stored in the slot 'name'.
	 */
	@Override
	public double value(String name) {
		Slot s = sheet.get(name);
		if( (s != null) && !(s instanceof CommentSlot) ){
			ExprParser parser = new ExprParser();
	        try {
	        	Expr expr = parser.build(s.getContent());
	        	return expr.value(this);
	        }catch(IOException e){
	        	//TODO make it do things on IOException
	        }
		}
		return 0;
	}
	
	/**
	 * Clear a specific key-value mapping and notify the Observers.
	 * @param name of the slot to be cleared.
	 */
	public void clear(String name){
		if(sheet.containsKey(name)){
			sheet.remove(name);
			this.setChanged();
			this.notifyObservers();
			this.clearChanged();
		}
		
	}
	
	/**
	 * Clear All key-values in the HashMap and notify the Observers.
	 */
	public void clearAll(){
		sheet.clear();
		this.setChanged();
		this.notifyObservers();
		this.clearChanged();
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

}
