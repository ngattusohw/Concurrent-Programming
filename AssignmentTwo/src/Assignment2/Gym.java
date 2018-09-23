package Assignment2 ;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Gym implements Runnable{

	private static final int GYM_SIZE = 30;
	private static final int GYM_REGISTERED_CLIENTS = 10000;
	private Map<WeightPlateSize, Integer> noOfWeightPlates;
	private Set<Integer> clients; // for generating fresh client ids
	private ExecutorService executor;
	// various semaphores - declaration omitted
	
	//where the actual simulator happens
	public void run() {
		//ExecutorService es = Executors.newCachedThreadPool();
		//es.execute(classList.get(classList.size()-1));
		//es.shutdown();
//		try {
//		while(!es.awaitTermination(1, TimeUnit.MINUTES)){}
//	} catch (InterruptedException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
		noOfWeightPlates = new HashMap<WeightPlateSize, Integer>();
		noOfWeightPlates.put(WeightPlateSize.SMALL_3KG,110);
		noOfWeightPlates.put(WeightPlateSize.MEDIUM_5KG,90);
		noOfWeightPlates.put(WeightPlateSize.LARGE_10KG,75);
		
		ExecutorService es = Executors.newFixedThreadPool(GYM_SIZE);
		for(int x=0; x<Gym.GYM_REGISTERED_CLIENTS; x++) {
			es.execute(Client.generateRandom(x, noOfWeightPlates));
		}
	}
}
