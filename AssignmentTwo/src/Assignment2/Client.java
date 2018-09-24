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
	
	public static void instantiateSemaphores() {
		mutexLock = new Semaphore(1);
		printing = new Semaphore(1, true);
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
		
		for(Exercise the_exercise : routine){
			int duration = the_exercise.getDuration();
			Map<WeightPlateSize, Integer> weight = the_exercise.getWeight();
			int at_index = the_exercise.getApparatusType().ordinal();
			List<Integer> listWeight = new ArrayList<>(weight.values());
			
			try {
				mutexLock.acquire();
				apparatusSemaphore[at_index].acquire();
				for(int weightType = 0; weightType < 3; weightType++) {
					for(int amountOfWeights = 0; amountOfWeights < listWeight.get(weightType); amountOfWeights++) {
						//do the acquires here for each weight
						weightSemaphore[weightType].acquire();
					}
				}
				
				printing.acquire();
				System.out.printf("Client %d has started working out with the %s with %d small weights, %d medium weights , %d large weights \n",
						this.id, the_exercise.getApparatusType().toString(),listWeight.get(0),listWeight.get(1),listWeight.get(2));
				printing.release();
				mutexLock.release();
				
				Thread.sleep(duration);
				printing.acquire();
				
			}catch(Exception e) {
				System.out.println("There was an error!");
				System.exit(0);
			}
			
			System.out.printf("Client %d has stopped working out with the %s with %d small weights, %d medium weights , %d large weights \n",
					this.id, the_exercise.getApparatusType().toString(),listWeight.get(0),listWeight.get(1),listWeight.get(2));
			printing.release();
			apparatusSemaphore[at_index].release();
			for(int weightType = 0; weightType < 3; weightType++) {
				for(int amountOfWeights = 0; amountOfWeights < listWeight.get(weightType); amountOfWeights++) {
					//do the release here for each weight
					weightSemaphore[weightType].release();
				}
			}
			
		}
	}
}
