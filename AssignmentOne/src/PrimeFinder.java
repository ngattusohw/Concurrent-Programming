import java.util.ArrayList;
import java.util.List;

public class PrimeFinder implements Runnable{

	private Integer start ;
	private Integer end ;
	private List<Integer> primes;
	
	// Constructs a PrimeFinder
	public PrimeFinder(Integer startNum , Integer endNum) {
		start = startNum;
		end = endNum;
		primes = new ArrayList<Integer>();
	}
	
	// Returns the value of the attribute primes
	public List<Integer> getPrimesList(){
		return primes;
	}
	
	public void printPrimeList() {
		System.out.println("The Primes for this thread are");
		for(Integer yeet: primes) {
			System.out.println(yeet + ",");
		}
	}

	// Determines whether its argument is prime or not
	public Boolean isPrime(int n){
	    if (n%2==0) {
	    	return false;
	    }
	    for(int i=3;(i*i)<=n;i+=2) {
	        if(n%i==0) {
	            return false;
	        }
	    }
	    return true;
	}
	
	// Adds all primes in [ this . start , this . end ) to the attribute primes
	public void run () {
		System.out.println("This is me, I am in the thread!");
		for(int s=start;s<=end;s++) {
			if(isPrime(s)) {
				primes.add(s);
			}
		}
	}
	
//	public static void main(String args[]) {
//		
//	}
	
//	Runnable runnable = new PrimeFinder(start,end);
//	Thread thread = new Thread(runnable);
//	thread.start();
}
