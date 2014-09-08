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
    private final double distance;
    private final double confidence;
    private final int stintno;
    private final int half;
    


    
    /**
     * Constructor.
     * @param startTime
     * @param endTime
     * @param number
     * @param half
     * @param distance
     */
    public Stint(double startTime, double endTime,double distance,,double confidence,int number,int half) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.distance = distance;
        this.confidence = confidence;
        this.stintno = number;
        this.half = half;

        
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

    /**
     * Get accessor for the stint's total distance.
     * @return 
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Get accessor for the stint number.
     * @return 
     */
    public int getNumber() {
        return stintno;
    }

    /**
     * Get accessor for the stint's game half.
     * @return 
     */
    public int getHalf() {
        return half;
    }

}
