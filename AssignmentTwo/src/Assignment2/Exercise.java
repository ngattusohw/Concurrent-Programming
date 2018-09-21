package Assignment2 ;

import java.util.Map;

public class Exercise {
	private ApparatusType at;
	private Map<WeightPlateSize, Integer> weight;
	private int duration;

	
	public Exercise(ApparatusType at, Map<WeightPlateSize, Integer> weight, int duration) {
		this.at = at;
		this.weight = weight;
		this.duration = duration;
	}
	
	public static Exercise generateRandom(Map<WeightPlateSize, Integer> weight) {
		return null;
	}

}
