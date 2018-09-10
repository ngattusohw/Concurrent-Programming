import java.util.ArrayList;
import java.util.List;

/*
 * I pledge my honor that I have abided by the stevens honor system
 */
public class PrimeFinder implements Runnable{

	private Integer start;
	private Integer end;
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
	
	// Determines whether its argument is prime or not
	public Boolean isPrime(int n){
	    if (n%2==0 && n!=2) {
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
		for(int s=start;s<end;s++) {
			if(isPrime(s)) {
				primes.add(s);
			}
		}
	}
}
