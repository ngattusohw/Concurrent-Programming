package Assignment2 ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Client implements Runnable{
	private int id;
	private List<Exercise> routine;
	private static Semaphore mutexLock;
	private static Semaphore printing;
	private static Semaphore[] apparatusSemaphore;
	private static Semaphore[] weightSemaphore;

	
	public Client(int id) {
		this.id = id;
		this.routine = new ArrayList<Exercise>();
	}
	
	public static void instansiateSemaphores() {
		mutexLock = new Semaphore(1);
		printing = new Semaphore(1);
		apparatusSemaphore = new Semaphore[8];
		for(int x = 0; x < 8; x++) {
			apparatusSemaphore[x] = new Semaphore(5);
		}
		
		Semaphore[] tempWeight = {
				new Semaphore(110),
				new Semaphore(90),
				new Semaphore(75)
		};
		weightSemaphore = tempWeight;
	}
	
	public static Map<WeightPlateSize, Integer> generateRandomWeightPlate() {
		int sum;
		int[] atLeastOne;
		do{
			int[] atLeastOneTEMP = {
					(int) (Math.random() * 11),
					(int) (Math.random() * 11),
					(int) (Math.random() * 11)
			};
			sum = atLeastOneTEMP[0] + atLeastOneTEMP[1] + atLeastOneTEMP[2];
			atLeastOne = atLeastOneTEMP;
		}while(sum==0);
		
		Map<WeightPlateSize, Integer> the_map = new HashMap<WeightPlateSize, Integer>();
		the_map.put(WeightPlateSize.SMALL_3KG,atLeastOne[0]);
		the_map.put(WeightPlateSize.MEDIUM_5KG,atLeastOne[1]);
		the_map.put(WeightPlateSize.LARGE_10KG,atLeastOne[2]);
		
		return the_map;
	}
	
	/**
	 * Adds a new exercise to the clients routine
	 * @param e
	 */
	public void addExercise(Exercise e) {
		routine.add(e);
	}
	
	/**
	 * Generate a new random client, with a routine of 15-20 random exercises
	 * @param id
	 * @param noOfWeightPlates...does nothing
	 * @return Client
	 */
	public static Client generateRandom(int id, Map<WeightPlateSize, Integer> noOfWeightPlates) {
		Client newClient = new Client(id);
		int randomNumExe = (int) ((Math.random() * 6) + 15);//randomly between 15 and 20
		//make random Weight Plate 
		Map<WeightPlateSize, Integer> randomNoOfWeightPlates = Client.generateRandomWeightPlate();
		
		for(int x = 0; x < randomNumExe; x++) {
			newClient.addExercise(Exercise.generateRandom(randomNoOfWeightPlates));
		}
		return newClient;
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
	}
	
}
