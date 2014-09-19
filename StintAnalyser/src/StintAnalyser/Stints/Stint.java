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

    private final int startTime;
    private final int endTime;
    private final int stintno;
    private final int period;

    /**
     * Constructor.     
     * @param startTime
     * @param endTime
     * @param number
     * @param period
     */
    public Stint(int startTime, int endTime, int number, int period) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.stintno = number;
        this.period = period;
    }

    /**
     * Get accessor for the stint's start time.
     *     
* @return
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Get accessor for the stint's end time.
     *     
* @return
     */
    public int getEndTime() {
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
    
}
