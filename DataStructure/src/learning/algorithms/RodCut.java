package learning.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Rod Cutting Dynamic Programming
 * 					Recursively solve with Memorization.
 * 
 * 1. Top-down   "depth-first search"
 * 		recursive call to depth-first, by memorization and recursive call to get depth subproblems, rather than "for" loop to solve all subproblems.	
 * 		1. check each time before subproblem computation, "only compute once" for all subproblems.
 * 		2. store solution into Memorization array r[n] each time after solving a subproblem.
 * 		3. using comparison to get the Maximum value "optimal" solution for Dynamic Programming:
 * 			  create variable q to store the "Current Maximum" value in each computation.
 * 
 * 2. bottom-up   "breadth-first search"
 * 		no recursive call, only by memorization, and "for" loop to solve all subproblems one by one rather than recursive call.
 * 		solve from the smallest subproblem "base case" to the largest problem "original",
 * 			so all needed solutions are stored into memorization for next subproblem.
 * 
 *
 * Both of their running time is O(n^2).
 * 		1. Both of them compute all n subproblems (r1 to rn) exactly once, totally n times.
 * 		2. Each subproblem takes O(n) time. (from 1 to n time for r1 to rn).
 * 	total running time = O(n(n+1)/2) = O(n^2).
 * 
 *  **/

public class RodCut {
	//Top down only return current level solution. "less memory, more time to get all solutions"
	public static int Topdown(int[] p, int n, int[] r) {
		int q;
		if (r[n] > 0) {
			//check whether it has been computed or not.
			return r[n];
		} else {
			//if haven't been computed, compute it once and store solution into Memorization.
			if (n == 0) {
				//init q, and r[0]
				q = 0;
			} else {
				q = -1;
				//rn = Max(1¡Üi¡Ün)(p[i] + r[n-i])
				//use "for" loop to implement 1¡Üi¡Ün, use local variable q to store current Max value.
				for (int i=1; i<=n; i++) {
					//use recursive call to "depth-first search".
					if (q < (p[i] + Topdown(p, n-i, r))) {
						q = p[i] + Topdown(p, n-i, r);
					}
				}
			}
			r[n] = q;
			return q;
		}
	}
	
	//Top down return solutions from level 0 to current level. "less time, more memory to get all solutions"
	public static int[] topdown(int[] p, int n, int[] r) {
		int q;
		if (r[n] > 0) {
			//check whether it has been computed or not.
			return r;
		} else {
			//if haven't been computed, compute it once and store solution into Memorization.
			if (n == 0) {
				//init q, and r[0]
				q = 0;
			} else {
				q = -1;
				//rn = Max(1¡Üi¡Ün)(p[i] + r[n-i])
				//use "for" loop to implement 1¡Üi¡Ün, use local variable q to store current Max value.
				for (int i=1; i<=n; i++) {
					//use recursive call to depth search.
					if (q < (p[i] + topdown(p, n-i, r)[n-i])) {
						q = p[i] + topdown(p, n-i, r)[n-i];
					}
				}
			}
			r[n] = q;
			return r;
		}
	}
	
		//Top down only return solution & solution details.
		public static int[] topdown(int[] p, int n, int[] r, String[] s) {
			int q;
			if (r[n] > 0) {
				//check whether it has been computed or not.
				return r;
			} else {
				//if haven't been computed, compute it once and store solution into Memorization.
				if (n == 0) {
					//init q, and r[0], s[0].
					q = 0;
					s[0] = "0";
				} else {
					q = -1;
					int cur = -1;
					int rec = -1;
					//rn = Max(1¡Üi¡Ün)(p[i] + r[n-i])
					//use "for" loop to implement 1¡Üi¡Ün, use local variable q to store current Max value.
					for (int i=1; i<=n; i++) {
						//use recursive call to depth search.
						if (q < (p[i] + topdown(p, n-i, r, s)[n-i])) {
							q = p[i] + topdown(p, n-i, r, s)[n-i];
							//store the current solution details
							cur = i;
							rec = n-i;
						}
					}
					//store details only by memorization.
					s[n] = " [" + String.valueOf(cur) + " " + s[rec] + "] ";
				}
				r[n] = q;
				return r;
			}
		}
	
	
	//bottom up, no recursively call, only by memorization all previous solutions
	//cause it solves all subproblems one by one, from smallest to largest, so it already memorizes all needed solutions.
	public static int bottomup(int[] p, int n, int[] r) {
		for (int i=1; i<=n; i++) {
			//init q
			int q = -1;
			for (int j=1; j<=i; j++) {
				//q = Max(q, p[i] + r[i-j]), store current Maximum value of solution
				if (q < (p[j] + r[i-j])) {
					q = p[j] + r[i-j];
				}
			}
			r[i] = q;
		}
		return r[n];
	}
	
		//bottom up All level solutions & solution details.
		public static int[] bottomup(int[] p, int n, int[] r, String[] s) {
			for (int i=1; i<=n; i++) {
				//init q
				int q = -1;
				int cur = -1;
				int rec = -1;
				for (int j=1; j<=i; j++) {
					//q = Max(q, p[i] + r[i-j]), store current Maximum value of solution
					if (q < (p[j] + r[i-j])) {
						q = p[j] + r[i-j];
						//store the current solution details
						cur = j;
						rec = i-j;
					}
					
				}
				r[i] = q;
				//store details only by memorization.
				System.out.println(String.valueOf(cur) + " " + rec);
				if (rec == 0) {
					s[rec] = "0";
				}
				s[i] = " [" + String.valueOf(cur) + " " + s[rec] + "] ";
			}
			return r;
		}
	
	
	public static void main(String[] args) {
		//memorize array
		int[] r = new int[11];
		for (int i=0; i<11; i++) {
			r[i] = 0;
		}

		//solution details
		String[] stopdown = new String[11];
		String[] sbottomup = new String[11];
		
		
		//price array
		int[] p = new int[11];
		p[0] = 0;
		p[1] = 1;
		p[2] = 5;
		p[3] = 8;
		p[4] = 9;
		p[5] = 10;
		p[6] = 17;
		p[7] = 17;
		p[8] = 20;
		p[9] = 24;
		p[10] = 30;

		float s1 = System.nanoTime();
		int[] result1 = new int[11];
		result1 = topdown(p, 10, r, stopdown);
		System.out.println("Top down" + "\n" + "rn: " + Arrays.toString(result1) + "\n" + "solution details: " + Arrays.toString(stopdown));
		float t1 = System.nanoTime();
		System.out.println("Topdown Time " + s1 + " " + t1);
		
		System.out.println();
		
		float s4 = System.nanoTime();
		int[] result2 = new int[11];
		result2 = bottomup(p, 10, r, sbottomup);
		System.out.println("Bottom up" + "\n" + "rn: " + Arrays.toString(result2) + "\n" + "solution details: " + Arrays.toString(sbottomup));
		float t4 = System.nanoTime();
		System.out.println("Time " + s4 + " to " + t4);

	}
}
