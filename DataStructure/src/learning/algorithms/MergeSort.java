package learning.algorithms;

import java.util.Arrays;


/*
 * Merge Sort		(additional space O(N))
 * 		divide and conquer
 * 
 * running time is жи(nlogn)
 * 
 * additional space is O(n)
 * 
 * **/

public class MergeSort {
	public static void merge(int[] s, int start, int mid, int end) {
		int[] tmp = new int[end-start + 1];
		
		//partition (divide) array into two parts.
		int i=start, j=mid+1, k=0;
		//add small one from two parts.
		for (; i<=mid && j<=end; k++) {
			if (s[i] < s[j]) {
				tmp[k] = s[i];
				i++;
			} else {
				tmp[k] = s[j];
				j++;
			}
		}
		
		//add the remaining element.
		while (i<=mid) {
			tmp[k] = s[i];
			i++;
			k++;
		}
		while (j<=end) {
			tmp[k] = s[j];
			j++;
			k++;
		}

		//copy sorted array to original.
		for (int t=0; t<tmp.length; t++) {
			s[start+t] = tmp[t];
		}
		
	}
	
	public static void Mergesort(int[] s, int start, int end) {
		if (start == end) {
			//stop the recursive, when reach the base case (bottom)
			return;
		} else {
			int mid = (start + end)/2;
			Mergesort(s, start, mid);
			Mergesort(s, mid+1, end);
			merge(s, start, mid, end);
		}
	}
	
	public static void main(String[] args) {
		int[] s={2,3,6,4,9,11,43,21,67,93,22,54,31};
		System.out.println(Arrays.toString(s));
		Mergesort(s, 0, s.length-1);
		System.out.println(Arrays.toString(s));
	}
}
