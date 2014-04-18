package learning.algorithms;

public class Palindrome {
	//check if Integer is palindrome or not.
	public static boolean isPalindrome(Integer s) {
		return isPalindrome(String.valueOf(s));
	}
	
	//check if String is palindrome or not, case insensitive.
	//1. reverse and compare to original.
	public static boolean isPalindrome(String s) {
		return new StringBuffer(s).reverse().toString().equalsIgnoreCase(s);
	}

	//2. compare from two ends to middle. (Iteration check)
	public static boolean isPalindrome2(String s) {
		int len = s.length();
		for (int i = 0; i<=len/2; i++) {
			if (s.charAt(i) != s.charAt(len-1-i)) {
				return false;
			}
		}
		return true;
	}
	
	//3. recursive check
	public static boolean isPalindrome3(String s) {
		if (s.length() == 0 || s.length() == 1) {
			return true;
		} else {
			if (s.charAt(0) == s.charAt(s.length()-1)) {
				System.out.println(s.length());
				//substring(begin index, end index). the end index will not include in the substring.
				//end index = last char index + 1.
				//begin index = first char index.
				return isPalindrome3(s.substring(1, s.length()-1));
			} else {
				return false;
			}
		}
	}
	
	public static void main(String[] args) {
		String s = "1234555321";
		System.out.println(isPalindrome3(s));
	}
}
