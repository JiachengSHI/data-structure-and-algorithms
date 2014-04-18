package learning.algorithms;

/*
 * Longest Palindrome Substring (LPS)
 * 			by Dynamic Programming
 * 
 * 
 * **/

public class LPS {
	/*Dynamic Programming
	 * 
	 * 1. top down (recursive call and memorization)
	 * 		running time: O(n^3), Memorization: O(n).
	 * 
	 *	1. know LPS(s-1), structure of LPS(s).
	 * 		s-1 + last char = s.
	 * 		q = LPS(end with last char), find the longest Palindrome in s which ended with last char.
	 * 		
	 * 		If LPS(s-1) < q, q = LPS(s).
	 * 		If LPS(s-1) > q, LPS(s-1) = LPS(s).
	 * 
	 * 2. store solutions of LPS(s) in Memorization String[] r.
	 * 
	 * 3. recursive to base case, s.length == 2, for all s.length == 1, is a Palindrome, so start from length == 2.
	 * 
	 * 		running time: O(n^3) = O(n^2) * validate palindrome time O(n).
	 * */
	public static String LPS(String s, String[] r) {
		int len = s.length();
		if (len == 0) {
			return null;
		} else {
			if (r[len] != null) {
				return r[len];
			} else {
				if (len == 2) {
					if (Palindrome.isPalindrome(s)) {
						r[2] = s;
						return s;
					} else {
						r[2] = s.substring(0,1);
						return s.substring(0,1);
					}
				} else {
					String q = "";
					for (int i=0; i<len-1; i++) {
						if (Palindrome.isPalindrome(s.substring(i, len))) {
							if (q.length() < len-i) {
								q = s.substring(i, len);
							}
						}
					}
					if (q.length() > LPS(s.substring(0,len-1), r).length()) {
						r[len] = q;
						return q;
					} else {
						r[len] = r[len-1];
						return LPS(s.substring(0,len-1), r);
					}
				}
			}
		}
	}

	
	/*
	 * bottom up (no recursive, all store in Memorization).
	 * 		running time: O(n^2), Memorization: O(n^2).   (space time trade off)
	 * 
	 * avoid re-computation in validating palindrome
	 * 
	 * p[i,j] is palindrome, when p[i+1,j-1] is palindrome and s[i] = s[j].
	 * 
	 * **/
	public static String LPS(String s) {
		int n = s.length();
		if (n == 0) {
			return null;
		} else {
			int begin = 0;
			int maxlen = 1;
			//boolean default value is all "false"
			boolean[][] table = new boolean[1000][1000];
			
			//two base case, lps length == 1 and 2.
			for (int i=0; i<n; i++) {
				table[i][i] = true;
			}
			
			for (int i=0; i<n-1; i++) {
				if (s.charAt(i) == s.charAt(i+1)) {
					table[i][i+1] = true;
					begin = i;
					maxlen = 2;
				}
			}
			
			//find all possible length of lps.
			for (int len=3; len<=n; len++) {
				//find all possible substrings.
				for (int i=0; i<n-len+1; i++) {
					//end index j.
					int j = i+len-1;
					if (s.charAt(i) == s.charAt(j) && table[i+1][j-1]) {
						table[i][j] = true;
						begin = i;
						maxlen = len;
					}
				}
			}

			return s.substring(begin, maxlen+begin);
		}
	}
	
	
	

	/*
	 * special one
	 * 
	 * use the characteristic of Palindrome.
	 * 		verify from two ends to center == verify from center to ends, but the number of possible ends >> possible centers.
	 * 		All possible centers in String s is O(2n-1) << possible ends O(n^2).
	 * 
 ***** Hint: verify one string is Palindrome or not is same,
	 * 			but verify all possible substrings are Palindrome or not is better to from center to ends, rather than ends to center.
	 * 
	 * 		1. expand from center to ends time for Palindrome in string s is O(n)
	 * 		2. all possible centers is O(2n-1)
	 * 
	 * 	total running time = O(2n-1) * O(n) = O(n^2).
	 * 		  Memorization: O(1), only store the longest palindrome found.
	 * 
	 * **/
	public static String expand(String s, int left, int right){
		int n = s.length();
		while (left >= 0 && right <= n-1 && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		//return previous one, cause this one is not verify.
		return s.substring(left+1, right);
	}
	
	public static String LPSCenter(String s) {
		int n = s.length();
		if (n == 0) {
			return null;
		} else {
			//Memorization for store currently LPS.
			String LPS = s.substring(0,1);
			
			for (int i=0; i<n-1; i++) {
				//center is char itself.
				String odd = expand(s, i, i);
				if (odd.length() > LPS.length()) {
					LPS = odd;
				}
				
				//center is space between two chars.
				String even = expand(s, i, i+1);
				if (even.length() > LPS.length()) {
					LPS = even;
				}
			}
			return LPS;
		}
	}
	
	


	/*
	 * Manacher's algorithm
	 * 		use the mirror symmetric of Palindrome
	 * 
	 *  the sub-palindromes inside a Palindrome should have a mirror copy in the other side of center, with same length.
	 *  no need to re-compute the mirror sub-palindrome inside the Palindrome range.
	 *  
	 *  if sub-palindrome outside the Palindrome, it need to compute, and move forward the Palindrome from old one to this new one.
	 * 
	 * **/
	public static String process(String s) {
		int n = s.length();
		String rset = "^";
		if (n == 0) {
			return rset += "$";
		} else {
			for (int i=0; i<n; i++) {
				rset += "#" + s.charAt(i);
			}
			return rset += "#$";
		}
	}
	
	public static String LPSMirror(String s) {
		String rset = process(s);
		int n = rset.length();
		int[] p = new int[n];
		int c = 0, r = 0;
		
		/*
		 * Consider all element is a new Palindrome center when it expand across the range of old one.
		 * 
		 * if it's not across, not need to re-compute p[i] = p[i_mirror].
		 * if it across, make it new center and compute the length of palindrome of itself as center.
		 * 
		 * 
		 * i from 1 to n-1, remove the prefix "^" and postfix "$".
		 *
		 ***/
		for (int i=1; i< n-1; i++) {
			int i_mirror = 2*c - i;
			
			//
			if (r < i) {
				p[i] = 0;
			} else {
				if (r-i > p[i_mirror]) {
					p[i] = p[i_mirror];
				} else {
					p[i] = r-i;
				}
			}
			
			//expand palindrome ar p[i], when r-i < p[i_mirror].
			while (rset.charAt(i+p[i] + 1) == rset.charAt(i-p[i] - 1)) {
				p[i]++;
			}
			
			//check if p[i] across the right bound r.
			if (i+p[i] > r) {
				c = i;
				r = i+p[i];
			}
		}
		
		//find max p[i] and i(center).
		int maxlen = 0;
		int center = 0;
		for (int i=0; i<n-1; i++) {
			if (p[i] > maxlen) {
				maxlen = p[i];
				center = i;
			}
		}
		
		//Iteration starts from index 1, so center - maxlen -1 = begin index.
		//end index = begin index + maxlen - 1. in substring, end = end index + 1 = begin index + maxlen.
		return s.substring((center - maxlen - 1)/2, maxlen + (center - 1 - maxlen)/2);
	}
	
	
	

	public static void main(String[] args) {
		String s = "";
		String[] r = new String[s.length()+1];
		
		float s1 = System.nanoTime();
		System.out.println(LPS(s, r));
		float t1 = System.nanoTime() - s1;
		System.out.println("Time " + s1 + " " + t1);
		
		System.out.println();
		
		float s2 = System.nanoTime();
		System.out.println(LPS(s));
		float t2 = System.nanoTime() - s2;
		System.out.println("Time " + s2 + " " + t2);
		
		System.out.println();
		
		float s3 = System.nanoTime();
		System.out.println(LPSCenter(s));
		float t3 = System.nanoTime() - s3;
		System.out.println("Time " + s3 + " " + t3);
		
		System.out.println();
		
		float s4 = System.nanoTime();
		System.out.println(LPSMirror(s));
		float t4 = System.nanoTime() - s4;
		System.out.println("Time " + s4 + " " + t4);
	}
}
