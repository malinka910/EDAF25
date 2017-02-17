package uppgift3;

public class Node<E> implements StackNode<E> {
	
	private E element;
	private StackNode<E> up;
	private StackNode<E> down;
	
	public Node(E e){
		this.element = e;
	}
	
	@Override
	public E getElement(){
		return element;
	}

	@Override
	public synchronized boolean setUp(StackNode<E> n) {
		up = n;
		return true;
	}

	@Override
	public synchronized boolean setDown(StackNode<E> n) {
		down = n;
		return true;
	}

	@Override
	public synchronized StackNode<E> getUp() {
		return up;
	}

	@Override
	public synchronized StackNode<E> getDown() {
		return down;
	}

}
