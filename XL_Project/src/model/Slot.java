package model;

/**
 * An abstraction for the container classes for spreadsheet content. 
 * The two implementations are ExpressionSlot and CommentSlot. 
 * @author Greg
 */
public interface Slot {
	
	/**
	 * The content of a slot is stored as a string, regardless if it's an expression or comment. 
	 * @return content String. 
	 */
	public String getContent();
	
	public double value(Spreadsheet sheet);

}
