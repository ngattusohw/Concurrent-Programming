import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/*
 * Nick Gattuso
 * I pledge my honor that I have abided by the stevens honor system
 */
public class AssignmentOne{
	public static boolean meetsCriteria(List<Integer[]> list){
		if(list.size()!= 0) {
			int[] arr = new int[list.size() + 1];
			int arrIterator = 0;
			int last = -1; // this will always be overwritten
			for(Integer[] hold : list) {
				arr[arrIterator] = hold[0];
				arrIterator++;
				last = hold[1];
			}
			arr[arrIterator] = last;
			
		    for(int i=0; i < arr.length -1;i++){
		        if(arr[i]>arr[i + 1]) {
		        	return false;
		        }else if(arr[i] < 2) {
		        	return false;
		        }
		    }
		    return true;
		}else {
			System.out.println("Providied List has no items!");
			return false;
		} 
	 }
	
	
	//List<Integer[]> intervals
	public static List<Integer> lprimes(List<Integer[]> intervals){
		List<Integer> finalAnswer;
		
		//ExecutorService es = Executors.newCachedThreadPool();
		List<PrimeFinder> classList = new ArrayList<PrimeFinder>();
		List<Thread> threadList = new ArrayList<Thread>();
		for(Integer[] temparr : intervals) {
			classList.add(new PrimeFinder(temparr[0],temparr[1]));
			threadList.add(new Thread(classList.get(classList.size()-1)));
			threadList.get(threadList.size()-1).start();
			//es.execute(classList.get(classList.size()-1));
		}
		//es.shutdown();
		
		
//		try {
//			while(!es.awaitTermination(1, TimeUnit.MINUTES)){}
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		for(Thread t : threadList) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		finalAnswer = new ArrayList<Integer>();
		for(PrimeFinder prime : classList) {
			for(Integer toAdd : prime.getPrimesList()) {
				finalAnswer.add(toAdd);
			}
		}
		
		return finalAnswer;
	}
	
	public static void main(String args[]) {
		if(args.length % 2 == 0){
			List<Integer[]> myList = new ArrayList<Integer[]>();
			for(int argsIterator = 0; argsIterator < args.length -1; argsIterator= argsIterator + 1){
				Integer[] temp = {Integer.parseInt(args[argsIterator]), Integer.parseInt(args[argsIterator+1])};
				myList.add(temp);
			}
			if(meetsCriteria(myList)) {
				List<Integer> thelprimes = lprimes(myList);
				
				System.out.print("[");
				boolean isFirstNumber = true;
				for(Integer print : thelprimes) {
					if(isFirstNumber) {
						System.out.print(print);
					}else{
						System.out.print("," + print);
					}
					isFirstNumber=false;
				}
				System.out.println("]");
			}else {
				System.out.println("List was not in increasing order!");
			}
		}else {
			System.out.println("Invalid amount of inputs!");
		}
	}
}


