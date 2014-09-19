package StintAnalyser.Stints;

import StintAnalyser.Data.GameTime;

/**
 * CITS3200 Professional Computing Stint.java Contains fields and methods
 * pertaining to stints. Stints are time periods within a game in which the
 * relevant player is on the field.
 *
 * @version 1.1 20/08/14
 * @author Group B
 */
public class Stint {

    GameTime startTime;
    GameTime endTime;
    int stintno;
    int period;

    /**
     * Constructor.     
     * @param startTime
     * @param endTime
     * @param number
     * @param period
     */
    public Stint(GameTime startTime, GameTime endTime, int number, int period) {
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
    public GameTime getStartTime() {
        return startTime;
    }

    /**
     * Get accessor for the stint's end time.
     *     
* @return
     */
    public GameTime getEndTime() {
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
