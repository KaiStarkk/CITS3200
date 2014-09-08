package StintAnalyser.Stints;

/**
 * CITS3200 Professional Computing Stint.java Contains fields and methods
 * pertaining to stints. Stints are time periods within a game in which the
 * relevant player is on the field.
 *
 * @version 1.1 20/08/14
 * @author Group B
 */
public class Stint {

	private final double startTime;
	private final double endTime;
	private final double distance;
	private final double confidence;
	private final int stintno;
	private final int period;

	/**
	 * Constructor.
	 *
	 * @param startTime
	 * @param endTime
	 * @param confidence
	 * @param number
	 * @param period
	 */
	public Stint(double startTime, double endTime, double confidence, int number, int period) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.confidence = confidence;
		this.stintno = number;
		this.period = period;
		
		//just wanted to get things compiling - Dean
		this.distance = 0;

	}

	/**
	 * Get accessor for the stint's start time.
	 *
	 * @return
	 */
	public double getStartTime() {
		return startTime;
	}

	/**
	 * Get accessor for the stint's end time.
	 *
	 * @return
	 */
	public double getEndTime() {
		return endTime;
	}

	/**
	 * Get accessor for the stint number.
	 *
	 * @return
	 */
	public int getNumber() {
		return stintno;
	}

	/**
	 * Get accessor for the stint's game half.
	 *
	 * @return
	 */
	public int getPeriod() {
		return period;
	}

	double getDistance() {
		throw new UnsupportedOperationException("Not supported yet."); 
		//just wanted to get things compiling - Dean
	}

	int getHalf() {
		throw new UnsupportedOperationException("Not supported yet."); 
		//just wanted to get things compiling - Dean
	}

}
