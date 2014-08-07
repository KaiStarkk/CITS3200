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
}