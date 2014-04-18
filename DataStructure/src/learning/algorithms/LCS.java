package learning.algorithms;


import java.util.Arrays;

public class LCS {
	//top down LCS
	public static String LCS(String x, int m, String y, int n, String[][] LCS) {
		if (m == 0 || n == 0) {
			return "";
		} else {
			if (LCS[m][n] != null) {
				return LCS[m][n];
			} else {
				if (x.charAt(m) == y.charAt(n)) {
					LCS[m][n] = LCS(x, m-1, y, n-1, LCS) + x.charAt(m);
					return LCS[m][n];
				} else {
					//max(LCS(x, m-1, y, n), LCS(x, m, y, n-1))
					if (LCS(x, m-1, y, n, LCS).length() < LCS(x, m, y, n-1, LCS).length()) {
						LCS[m][n] = LCS(x, m, y, n-1, LCS);
						return LCS[m][n];
					} else {
						LCS[m][n] = LCS(x, m-1, y, n, LCS);
						return LCS[m][n];
					}
				}
			}
		}
	}
	
	//LCCS Longest contiguous common substring
	public static String LCCS(String x, int m, String y, int n, String[][] LCS, String s) {
		for (int i=1; i<=m; i++) {
			for (int j=1; j<=n; j++) {
				if (x.charAt(i) == y.charAt(j)) {
					if (i == 0 || j == 0) {
						LCS[i][j] = String.valueOf(x.charAt(i));
						if (s.length() < LCS[i][j].length()) {
							s = LCS[i][j];
						}
					} else {
						LCS[i][j] = LCS[i-1][j-1] + x.charAt(i);
						if (s.length() < LCS[i][j].length()) {
							s = LCS[i][j];
						}
					}
				} else {
					LCS[i][j] = "";
				}
			}
		}
		return s;
	}
	
	public static void main(String[] args) {
		String x ="abcdeaaffebca";
		String y = "aacbffecabbcf"
				+ "";
		String[][] LCS = new String[x.length()][y.length()];
		String[][] LCCS = new String[x.length()][y.length()];
		for (int i=0; i<x.length(); i++) {
			for (int j=0; j<y.length(); j++) {
				LCCS[i][j] = "";
			}
		}
		String s = "";
		System.out.println(LCCS(x, x.length()-1, y, y.length()-1, LCCS, s));
		System.out.println();
		System.out.println(Arrays.deepToString(LCCS));
		
		System.out.println();
		
		System.out.println(LCS(x, x.length()-1, y, y.length()-1, LCS));
		System.out.println();
		System.out.println(Arrays.deepToString(LCS));
	}
}
