package learning.algorithms;

import java.util.Arrays;

/*
 * Heap Sort	(Inner Sorting)
 * 	1. build heap
 * 	2. extract and rebuild heap (heapify). change current root with current tail.
 * 
 * 	character of complete binary tree:
 * 		left = index * 2 + 1;
 * 		right = left + 1 = index * 2 + 2;
 * 		parent = (index - 1) / 2;
 * 
 * 	each level n has 2^n nodes.
 * 
 * 
 * 
 * 	running time: O(n) + O(nlogn) = O(nlogn)
 * 
 * 	additional space: O(1), "inner sorting"
 * 
 * 	ad:
 * 		1. best for choose Max or Min value from data.
 * 		2. additional space is O(1).
 * 		3. running time is O(nlogn).
 * 	disad:
 * 		1. need to build heap first, not suitable for small amount of data.
 * 
 * ***/

public class HeapSort {
	//swap two elements in array.
	public static void swap(int[] s, int index1, int index2) {
		int tmp = s[index1];
		s[index1] = s[index2];
		s[index2] = tmp;
	}
	
	//Build Max-Heap.
	public static void BuildHeap(int[] s) {
		int n = s.length;
		//heapify all nodes from top to bottom. (except root)
		for (int i=1; i<n; i++) {
			int index = i;
			//heapify from node position to root.
			while (index > 0) {
				int parent = (index-1)/2;
				if (s[parent] < s[index]) {
					//swap parent with child
					swap(s, index, parent);
				} else {
					//break when parent is smaller.
					break;
				}
				//continue heapify until root, index 0.
				index = parent;
			}
		}
	}
	
	//Heapify and relocation
	public static void Heapify(int[] s) {
		int tail = s.length-1;
		for (int i=0; i<s.length-1; i++) {
			//swap Current Max to Current tail each time. Move tail forward each time.
			swap(s, 0, tail);
			tail--;

			//heapify, rebuild heap(relocation root).
			int index = 0;
			//have left child (have child).
			while (index * 2 + 1 < tail) {
				int left = index * 2 + 1;
				int right = left + 1;
				//have both left, right child.
				if (right <= tail) {
					//swap with Max one
					if (s[left] < s[right]) {
						int tmp = s[index];
						s[index] = s[right];
						s[right] = tmp;
						index = right;
					} else {
						int tmp = s[index];
						s[index] = s[left];
						s[left] = tmp;
						index = left;
					}
				} else {
					int tmp = s[index];
					s[index] = s[left];
					s[left] = tmp;
					index = left;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] s = {1,2,3,4,5,6,7,8,9,10};
		System.out.println("Array: "+Arrays.toString(s));
		System.out.println();
		BuildHeap(s);
		System.out.println("heapify: "+Arrays.toString(s));
		System.out.println();
		Heapify(s);
		System.out.println("heapify: "+Arrays.toString(s));
	}
}
