package uppgift3;

public class NullNode<E> implements StackNode<E> {

	StackNode<E> up;
	
	@Override
	public E getElement(){
		return null;
	}
	
	@Override
	public boolean setUp(StackNode<E> n) {
		up = n;
		return true;
	}

	@Override
	public boolean setDown(StackNode<E> n) {
		return false;
	}

	@Override
	public StackNode<E> getUp() {
		return up;
	}

	@Override
	public StackNode<E> getDown() {
		return this;
	}

}
