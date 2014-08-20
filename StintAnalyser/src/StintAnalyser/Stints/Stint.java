package StintAnalyser.Stints;

/**
 * CITS3200 Professional Computing
 * Stint.java
 * Contains fields and methods pertaining to stints. Stints are time periods
 * within a game in which the relevant player is on the field.
 * @version 1.1 20/08/14
 * @author Group B
 */
public class Stint {
    
    private final double startTime;
    private final double endTime;
    
    /**
     * Constructor.
     * @param startTime
     * @param endTime
     */
    public Stint(double startTime, double endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    /**
     * Get accessor for the stint's start time.
     * @return 
     */
    public double getStartTime() {
        return startTime;
    }
    
    /**
     * Get accessor for the stint's end time.
     * @return 
     */
    public double getEndTime() {
        return endTime;
    }
}
