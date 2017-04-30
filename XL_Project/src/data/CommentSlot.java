package data;

/**
 * Container for comments.
 * @author Greg
 *
 */
public class CommentSlot implements Slot {
	
	String content;
	
	/**
	 * Constructor for the CommentSlot.
	 * @param content is not checked for errors, error checking needs to be done before construction.
	 */
	public CommentSlot(String content){
		this.content = content;
	}

	/**
	 * Get the slot content.
	 */
	@Override
	public String getContent() {
		return content.toString();
	}

}
