/**
 * CITS3200 Professional Computing
 * Analyser.java
 * Purpose: Takes input from the GPS, Time and Accelerometer class to determine if 
 *          a player is playing on the field during a game or not.
 *
 * @version 1.0 07/08/14
 * @author Group B
 */

public class Analyser{

/**
 * This method uses a combination of player GPS and Accelerometer data in conjuction with data about the game time to determine 
 * if a particular period represents a section that the player was playing on field or not.
 *
 * @param gameTime This input indicates whether the period we are interested in lines within play time or not.
 * @param playerActivity This input based on the accelerometer data tells us whether a player was active or inactive during our interested period.
 * @param fieldCorners This input tells us the GPS coordinates of the field and by comparing it to the player's coordinates, we can tell if he or she was on the field or not.
 * 
 *
 */
    public static void dataAnalyser (int gameTime, int playerActivity, double[][] fieldCorners){
        
    }

}

=======
public class Analyser{
	
	private Gps gps;
	private Time time;
	private Accelerometers accel;
	
	/**
	 *
	 *
	 *
	 */
	public Analyser(Dataset dataset){
		gps = new Gps();
		time = new Time();
		accel = new Accelerometers();
	}
    >>>>>>> FETCH_HEAD
}