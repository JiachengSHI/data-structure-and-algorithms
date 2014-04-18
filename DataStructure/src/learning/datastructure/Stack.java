package learning.datastructure;

import learning.datastructure.LinkedList.ListNode;

/*
 * Stack
 * 
 * Stack is a singly-Linked List, FILO/LIFO.
 * 
 * top is its head.
 * 
 * push() is add(), always add new Node at head (top) -> LIFO.
 * pop() is remove(), always remove from head (top). -> LIFO.
 * top() is head.item().
 * 
 * **/
public class Stack {
	private LinkedList s;
	private ListNode top;
	
	public Stack() {
		s = new LinkedList();
		top = null;
	}

	//add head
	public void push(Object item) {
		s.insertHead(item);
		//update top
		top = s.head;
	}
	
	//remove head
	public Object pop() {
		if (this.isEmpty()) {
			//System.err.println("underflow");
			return null;
		} else {
			Object result = top.getitem();
			s.deleteHead();
			//update top
			top = s.head;
			return result;
		}
	}
	
	public boolean isEmpty() {
		return s.size == 0;
	}
	
	public void display() {
		s.display();
	}
	
	public static void main(String[] args) {
		Stack s = new Stack();
		s.push(0);
		s.push(1);
		s.push(2);

		s.display();
		System.out.println();
		System.out.println(s.top);
		System.out.println();
		
		s.pop();
		s.display();
		System.out.println();
		System.out.println(s.top);
	}
}
