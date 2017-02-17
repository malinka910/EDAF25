package uppgift3;

public interface StackNode<E> {
	
	/* link node n on top of this node */
	public E getElement();

	/* link node n on top of this node */
	public boolean setUp(StackNode<E> n);
	
	/* link node n on bottom of this node */
	public boolean setDown(StackNode<E> n);
	
	/* return node that is linked on top */
	public StackNode<E> getUp();
	
	/* return node that is linked on bottom */
	public StackNode<E> getDown();
	
}
