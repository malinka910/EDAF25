package uppgift3;

public class MyStack<E>{
	
	private StackNode<E> top;
	private int size;
	
	public MyStack(){
		size = 0;
		this.top = new NullNode<E>();
	}
	
	public E push(E e){
		StackNode<E> n = new Node<E>(e);
		top.setUp(n);
		n.setDown(top);
		top = n;
		size++;
		return e;
	}
	
	public E peek(){
		return top.getElement();
	}
	
	public E pull(){
		StackNode<E> ret = top;
		top = top.getDown();
		if(size>0){
			size--;
		}
		return ret.getElement();
	}
	
	
}
