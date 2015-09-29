package math;

public class Primes {
	/**
	 * Find the next positive prime after the given starting point. If startPoint is negative, return null.
	 * @param startPoint the number after which to search
	 * @return the next prime after <code>startPoint</code>
	 */
	public static int findNextPrime(int startPoint) {
		if(startPoint<0) return (Integer) null;
		if(startPoint<=1) return 2;
		startPoint++;
		if(startPoint%2==0) startPoint++;
		while(!isPrime(startPoint)) {
			startPoint+=2;
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
