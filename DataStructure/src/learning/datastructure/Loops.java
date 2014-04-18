package learning.datastructure;

/* for loop is equivalent with while loop,
 * for loop:
 * 	for(initialize, test, iteration) {
 *		statement;
 * 	}
 *
 * while loop:
 * 	initialize;
 * 	while(test) {
 *		statement;
 * 	}
 * 
 * */

public class Loops {
	//while loop runs as long as loop condition remains true.
	public static boolean isPrime(int n) {
		int divisor =2;
		while (divisor < n){
			if (n%divisor == 0){
				//System.out.println("divisor is "+divisor);
				return false;
			}
			divisor++;
		}
		return true;
	}
	
	public static int findPrime() {
		int prime = 0;
		int m = 3;
		while (m < 100000){
			if (isPrime(m) == true) {
				prime = m;
			}
			//never forget to change argument of loop condition, otherwise it will cause infinite loop and never get out.
			m++;
		}
		return prime;
	}
	
	public static void main(String[] args){
		System.out.println(findPrime());
	}
}
