package learning.algorithms;

/*
 * Counting Sort  -  essence: 1. range n   2. n bucket.
 * 
 * 		using array itself as buckets
 * 
 * sort range n with n buckets.
 * count occurrence by add n, which can get rid of current stored value.
 * 
 * running time: O(n)
 * 
 * **/

public class FrequencyModify {
	public static void frequency(int[] s, int n) {
		//counting sort, range n, and n buckets(array with n index). count by add n for occur each time.
		//i from 0 to n-1 if last one = n. if last one < n, then i from 0 to n.
		for (int i=0; i<n-1; i++) {
			s[(s[i]-1)%n] += n;
		}
		
		//get the counting result by divide n.
		for (int i=0; i<n; i++) {
			//System.out.println(s[i]-n);
			int element = i+1;
			int frequency = s[i]/n;
			
			System.out.println("element: " + element + " frequency: " + frequency);
		}
	}
	
	
	public static void main(String[] args) {
		int[] s={1,2,3,3,5,5,7};
		frequency(s, s.length);
	}
}
