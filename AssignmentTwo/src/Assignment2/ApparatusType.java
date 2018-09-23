package Assignment2 ;

import java.util.Random;

public enum ApparatusType {
	LEGPRESSMACHINE , BARBELL , HACKSQUATMACHINE , LEGEXTENSIONMACHINE ,
	LEGCURLMACHINE , LATPULLDOWNMACHINE , PECDECKMACHINE ,
	CABLECROSSOVERMACHINE;
	
	public static ApparatusType getRandom() {
		int randomNumber  = (int) (Math.random() * 8);
		return ApparatusType.values()[randomNumber];
	}

}
