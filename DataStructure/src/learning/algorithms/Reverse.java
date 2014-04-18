package learning.algorithms;

import java.util.Arrays;

public class Reverse {
	//XOR with all 1...1 bits, returns Complement
	public static void complementBit(int[] s) {
		int[] tmp = new int[s.length];
		for (int i=0; i<tmp.length; i++) {
			tmp[i] = 1;
		}
		
		for (int j=0; j<s.length; j++) {
			s[j] ^= tmp[j];
		}
	}
	
	public static void reverseBit(int[] s) {
		
	}
	
	public static void main(String[] args) {
		int[] s ={1,1,1,0,0,1,0,1,1,0,0,0,1,0,0,1};
		System.out.println(Arrays.toString(s));
		complementBit(s);
		System.out.println(Arrays.toString(s));
	}
}
