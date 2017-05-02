package data;

import expr.ExprParser;
import util.XLException;

/**
 * Container for expressions
 * @author Greg
 *
 */
public class ExpressionSlot implements Slot {
	
	String content;

	/**
	 * Constructor for the ExpressionSlot.
	 * @param content is not checked for errors, error checking needs to be done before construction.
	 */
	public ExpressionSlot(String content) {
		this.content = content;
	}

	/**
	 * Get the content of this slot.
	 */
	@Override
	public String getContent() {
		return content.toString();
	}

	@Override
	public double value(Spreadsheet sheet) {
		try{
			return new ExprParser().build(content).value(sheet);
		}catch(Exception e){
			throw new XLException(e.getMessage());
		}
	}

}
