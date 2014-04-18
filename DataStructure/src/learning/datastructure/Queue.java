package learning.datastructure;

/*
 * Queue
 * 
 * Queue is an SLL, FIFO/LILO.
 * 
 * head is its first element, tail is its last element.
 * 			remove head, add tail. -> FIFO, LILO.
 * 
 * Equeue() is add(), always add new Node at tail (last) -> LILO.
 * Dqueue() is remove(), always remove from head (first) -> FIFO.
 * 
 * **/
public class Queue {
	private LinkedList q;
	
	public Queue() {
		q = new LinkedList();
	}
	
	public void Equeue(Object item) {
		q.insertTail(item);
	}
	
	public Object Dqueue() {
		if (this.isEmpty()) {
			System.err.println("underflow");
			return null;
		} else {
			Object result = q.head.getitem();
			q.deleteHead();
			return result;
		} 
	}
	
	public boolean isEmpty() {
		return q.size == 0;
	}
	
	public void display() {
		q.display();
	}
	
	public static void main(String[] args) {
		Queue q = new Queue();
		q.Equeue(1);
		q.Equeue(2);
		q.Equeue(3);

		q.display();
		System.out.println();
		
		q.Dqueue();
		q.Dqueue();
		q.display();
		
		System.out.println();
		q.Dqueue();
		System.out.println(q.isEmpty());
	}
}
