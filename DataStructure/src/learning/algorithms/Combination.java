package learning.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Combination
 * 
 * choose num elements from 1 set
 * 
 * Recursively call subset(set.sublist(remain set), num-1, previous elements + current element) for each element.
 * until the set.size() == num, if set.size() < num, no possible Combination can be chosen from the remain set, so drop those wrong cases.
 * 
 * Choose from first element "0" to last possible element "set.size()-num", which remain set.siz() == num can get 1 Combination
 * 		for (int i=0; i <= set.size()-num; i++) {
 * 			//recursively call subset() on each remain set "set.subList(i+1, set.size())" with num-1.
 ******     //create new-level result  = current-level result + current element, rather than modify current-level result += current element.
 *			//cause current-level result will be used by other elements & their subsets.
 *			subset(set.subList(i+1, set.size()), num-1, result + set.get(i));
 *		}
 * 
 * */
public class Combination {
	int num;
	List<Integer> set;
	
	public Combination(int num, List<Integer> set) {
		this.num = num;
		this.set =set;
	}
	
	public void subset(List<Integer> set, int num, String result) {		
		//no more digit need to combine
		if (num == 0) {
			System.out.println(result);
			System.out.println();
			return;
		}
		
		//left num digits need to combine from set, with previous result.
		for (int i=0; i<=set.size()-num; i++) {
			//System.out.println("i " + i + " num " + num + " result "+ result);
			subset(set.subList(i+1, set.size()), num-1, result + set.get(i));
		}
	}
	
	public static void main(String[] args) {
		//create class variables
		System.out.print("Enter set len: ");
		Scanner s = new Scanner(System.in);
		int len = s.nextInt();
		System.out.print("Enter Combination number: ");
		int num = s.nextInt();
		
		List<Integer> set = new ArrayList<Integer>();
		for (int i=1; i<=len; i++) {
			set.add(i);
		}
		
		//create combination obj
		Combination c = new Combination(num, set);
		System.out.println("Combine " + c.num + " elements from set " + c.set);
		System.out.println();
		//decide whether need to recursively combine or not
		if (c.set.size() < c.num) {
			System.out.println("Combination does not exist.");
		} else if (c.set.size() == c.num) {
			System.out.println(c.set);
		} else {
			//need to recursively combine
			c.subset(c.set, c.num, "");
		}
	}
}
