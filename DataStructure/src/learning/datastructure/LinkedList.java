package learning.datastructure;

public class LinkedList {
	class ListNode {
		private Object item;
		private ListNode next;
		
		public ListNode(Object item, ListNode next) {
			this.item = item;
			this.next = next;
		}
		
		public Object getitem() {
			return this.item;
		}
		
		/***
		public void insertAfter(int item) {
			next = new ListNode(item, next);
		}
		
		public ListNode nth(int position) {
			if (position == 1) {
				return this;
			} else if (position < 1 || next==null) {
				return null;
			} else {
				return next.nth(position-1);
			}
		}
		***/
		
		public void display() {
			System.out.print("[" + this.item + "]" + " -> ");
		}
	}
	
	protected int size;
	protected ListNode head;
	protected ListNode tail;
	
	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	//add or remove from head (stack)
	public void insertHead(Object item) {
		ListNode n = new ListNode(item, this.head);
		this.head = n;
		if (this.size == 0) {
			this.tail = n;
		}
		this.size++;
	}
	
	public void deleteHead() {
		if (this.head != null) {
			this.head = this.head.next;
			this.size--;
		} else {
			System.err.println("underflow");
		}
	}
	
	
	//add at tail, remove from head (queue)
	public void insertTail(Object item) {
		ListNode n = new ListNode(item, null);
		if (tail == null) {
			tail = n;
			head = n;
		} else {
			this.tail.next = n;
			this.tail = n;
		}
		this.size++;
	}

	
	//display
	public void display() {
		ListNode n = this.head;
		for (int i=0; i<this.size; i++) {
			n.display();
			n = n.next;
		}
		//System.out.println("size: " + size);
	}
	
	public static void main(String[] args){
		LinkedList a = new LinkedList();
		for (int i = 0; i < 11; i++) {
			a.insertHead(i);
		}
		a.display();
		System.out.println();
		a.deleteHead();
		a.display();
	}
	
}
