import java.util.List;

public class PrimeFinder implements Runnable{

	private Integer start ;
	private Integer end ;
	private List<Integer> primes ;
	
	// Constructs a PrimeFinder
	public PrimeFinder(Integer startNum , Integer endNum) {
		start = startNum;
		end = endNum;
	}
	
	// Returns the value of the attribute primes
	public List<Integer> getPrimesList(){
		
		Runnable runnable = new PrimeFinder(start,end);
		Thread thread = new Thread(runnable);
		thread.start();
		
		
		return null;
	}

	// Determines whether its argument is prime or not
	public Boolean isPrime(int n){
		return false;
	}
	
	// Adds all primes in [ this . start , this . end ) to the attribute primes
	public void run () {
		System.out.println("This is me, I am in the thread!");
		//primes = getPrimesList();
		
	}
	
//	public static void main(String args[]) {
//		
//	}
}
