import java.util.List;

public class AssignmentOne extends PrimeFinder{

	//default constructer
	public AssignmentOne() {
		super(1,2);
	}
	
	
	
	//List<Integer> intervals
	public static List<Integer> lprimes(){
		PrimeFinder test = new PrimeFinder(1,2);
		List<Integer> testicle = test.getPrimesList();
		return null;
	}
	
	public static void main(String args[]) {
		System.out.println("Whats up fuckers!");
		lprimes();
	}
}


