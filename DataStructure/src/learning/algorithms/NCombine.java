package learning.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * Combination
 * 
 * Combine elements each from n sets.
 * **/
public class NCombine {
	
	public static  List<String> subset(int[][] set, int n, List<String> result) {
		if (n == set.length-1) {
			List<String> tmp = new ArrayList<String>();
			for (int i : set[set.length-1]) {
				tmp.add(String.valueOf(i));
			}
			result = tmp;
			return tmp;
		} else {
			List<String> tmp = new ArrayList<String>();
			for (int i : set[n]) {
				for (String s : subset(set, n+1, result)) {
					//s = " [" + String.valueOf(i) + " " + s + "] ";
					s = String.valueOf(i) + s;
					tmp.add(s);
				}
			}
			result = tmp;
			return tmp;
		}
		
	}
	
	public static void main(String[] args) {
		int[][] set = new int[3][3];
		set[0][0]=0;
		set[0][1]=1;
		set[0][2]=2;
		set[1][0]=3;
		set[1][1]=4;
		set[1][2]=5;
		set[2][0]=6;
		set[2][1]=7;
		set[2][2]=8;
		List<String> result = new ArrayList<String>();
		System.out.println(subset(set, 0, result));
	}
}
