import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AssignmentOne extends PrimeFinder{

	
	
	//default constructer
	public AssignmentOne() {
		super(1,2);
	}
	
	
	//List<Integer[]> intervals
	public static List<Integer> lprimes(List<Integer[]> intervals){
		List<Integer> finalAnswer;
		
		
		ExecutorService es = Executors.newCachedThreadPool();
		List<PrimeFinder> classList = new ArrayList<PrimeFinder>();
		for(Integer[] temparr : intervals) {
			classList.add(new PrimeFinder(temparr[0],temparr[1]));
			es.execute(classList.get(classList.size()-1));
		}
		es.shutdown();
		
		
		try {
			while(!es.awaitTermination(1, TimeUnit.MINUTES)){}
			System.out.println("I am all done!");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		finalAnswer = new ArrayList<Integer>();
		for(PrimeFinder prime : classList) {
			for(Integer toAdd : prime.getPrimesList()) {
				finalAnswer.add(toAdd);
			}
		}
		
		System.out.print("[");
		for(Integer print : finalAnswer) {
			System.out.print(print + ",");
		}
		System.out.println("]");
		// all tasks have finished or the time has been reached.)
//		for(int i=0;i<5;i++)
//		    es.execute(new Runnable() { /*  your task */ });
//		es.shutdown();
		
		return null;
	}
	
	public static void main(String args[]) {
		System.out.println("Whats up fuckers!");
		List<Integer[]> myList = new ArrayList<Integer[]>();
		Integer[] fuck = {2,101};
		Integer[] fuckyou = {101,201};
		Integer[] fuckyou1 ={201,301};
		Integer[] fuckyou2 ={301,401};
		Integer[] fuckyou3 ={401,501};
		//List<int[]> fuckkkkkk = Arrays.asList({2,101},{101,201},{201,301},{301,401});
		myList.add(fuck);
		myList.add(fuckyou);
		myList.add(fuckyou1);
		myList.add(fuckyou2);
		myList.add(fuckyou3);
		lprimes(myList);
	}
}


