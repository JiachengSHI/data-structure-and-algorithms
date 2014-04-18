package learning.algorithms;

import java.util.Arrays;


/*
 * Quick Sort		(Inner Sorting)
 * 		Recursive partition
 * 
 * 		base case length == 3, left + mid + right.
 * 
 * 
 * 		running time: average O(nlogn), worst (sorted) O(n^2)
 * 		additional space: None O(1).
 * 
 * 
 * 	ad: fastest & space best sorting for average and large data.
 * 	disad:  If data is sorted, not a good choice.
 * 
 * **/

public class QuickSort {
	//swap two elements in array.
	public static void swap(int[] s, int index1, int index2) {
		int tmp = s[index1];
		s[index1] = s[index2];
		s[index2] = tmp;
	}
	
	public static int partition(int[] s, int start, int end) {
		int p2 = start + 1;
		for (int i=start+1; i<=end; i++) {
			if (s[i] < s[start]) {
				if (p2 < i) {
					swap(s, p2, i);
				}
				//move p2 forward.
				p2++;
			}
		}
		
		if(p2 == start + 1) {
			return start;
		} else if (p2 == end) {
			int mid = p2;
			//swap base number into mid.
			swap(s, start, mid);
			return mid;
		} else {
			int mid = p2-1;
			//swap base number into mid.
			swap(s, start, mid);
			return mid;
		}
	}
	
	public static void quicksort(int[] s, int start, int end) {
		//partition
		int mid = partition(s, start, end);
		//cases
		if (mid > start + 1 && mid < end - 1) {
			//recursive quicksort both two partitions.
			quicksort(s, start, mid-1);
			quicksort(s, mid+1, end);
		} else if (mid > start + 1 && mid >= end - 1) {
			quicksort(s, start, mid-1);
		} else if (mid <= start + 1 && mid < end) {
			quicksort(s, mid+1, end);
		}
	}
	
	public static void main(String[] args) {
		int[] s={2,3,6,4,9,11,43,21,67,93,22,54,31};
		quicksort(s, 0, s.length-1);
		System.out.println(Arrays.toString(s));
	}
}
