package learning.algorithms;

import java.util.Arrays;

/*
 * find odd frequency in all even frequency numbers
 * 
 * 		use XOR:
 * 			odd XOR = itself.
 * 			even XOR = 0.
 * 
 * only useful for two odd numbers.
 * **/

public class XOR {
	public static int findlowest1(int x) {
		int p = 0;
		while ((x & 1) != 1) {
			//find the lowest possible different bit, XOR=1.
			x >>= 1;
			p++;
		}
		return p;
	}
	
	public static int getbit(int x, int position) {
		//only return number at last bit & 1.
		return (x >> position) & 1;
	}
	
	public static void main(String[] args) {
		int[] s ={2,3,2,3,3,4,5,4,2,2,5,6};

		//result of odd XOR.
		Integer t = null;
		for (int i : s) {
			if (t == null) {
				//null point exception. null cannot ^ with any other number.
				t = i;
			} else {
				t ^= i;
			}
		}		

		//lowest different bit
		int position = findlowest1(t);

		//separate and result the odd number.
		Integer x0 = null,x1 = null;
		for (int i : s) {
			//separate two odd numbers into two groups.
			if (getbit(i, position) == 0) {
				//odd number with 0 at position, and all other even pairs.
				if (x0 == null) {
					x0 = i;
				} else {
					x0 ^= i;
				}
			} else {
				//odd number with 1 at position, and all other even pairs.
				if (x1 == null) {
					x1 = i;
				} else {
					x1 ^= i;
				}
			}
		}
		
		System.out.println("result: " + t);
		System.out.println("first different bit position: " + position);
		System.out.println("0 at position "+ position + " is " + x0);
		System.out.println("1 at position "+ position + " is " + x1);
		
	}
}
