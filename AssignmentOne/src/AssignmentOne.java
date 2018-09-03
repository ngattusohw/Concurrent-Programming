import java.util.List;

public class AssignmentOne extends PrimeFinder{

	//default constructer
	public AssignmentOne() {
		super(1,2);
	}
	
	
	//List<Integer[]> intervals
	public static List<Integer> lprimes(){
		PrimeFinder ass = new PrimeFinder(2,10);
		Thread thread1 = new Thread(ass);
		thread1.start();
		try {
			thread1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Integer> fuckingdicks = ass.getPrimesList();
		System.out.println("Eat my ass");
		for(Integer hold : fuckingdicks) {
			System.out.println("This is the shit:: " + hold);
		}
		
//		PrimeFinder test = new PrimeFinder(1,2);
//		List<Integer> testicle = test.getPrimesList();
		return null;
	}
	
	public static void main(String args[]) {
		System.out.println("Whats up fuckers!");
		lprimes();
	}
}


