package math;

public class Primes {
	public static int findNextPrime(int startPoint) {
		if(startPoint==1) return 2;
		startPoint++;
		if(startPoint%2 == 0) startPoint ++;
		while(!isPrime(startPoint)) {
			startPoint += 2;
		}
		return startPoint;
	}
	
	public static boolean isPrime(int x) {
		if(x==1) return false;
		for(int i=2; i<=Math.sqrt(x); i++) {
			if(x%i==0) return false;
		}
		return true;
	}
}
