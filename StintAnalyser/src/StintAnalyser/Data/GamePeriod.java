package StintAnalyser.Data;

/**
 * CITS3200 Professional Computing
 * GamePeriod.java
 * A period of time in a match.
 * Holds a pair of {@link GameTime}s. Abstracts as far as possible.
 * @version 1.0 19/09/14
 * @author Group B
 */
public class GamePeriod {
    GameTime start;
    GameTime end;
    
    /**
     * Constructor for the GamePeriod times using a String argument
     * @param line the line to be read
     * @throws IllegalArgumentException thrown if the length is of incorrect size
     */
    public GamePeriod(String line) throws IllegalArgumentException {
        String[] times = line.split("-");
        if (times.length != 2) {
            throw new IllegalArgumentException();
        }
        this.start = new GameTime(times[0]);
        this.end = new GameTime(times[1]);
    }
    
    /**
     * Secondary constructor where start and end times are already specified
     * @param start the start of the period
     * @param end the end of the period
     */
    public GamePeriod(GameTime start, GameTime end) {
        this.start = start;
        this.end = end;
    }
    
    /**
     * returns the start time of the period
     * @return the start time
     */
    public GameTime getStart() {
        return start;
    }
    
    /**
     * returns the end time of the period
     * @return the end time
     */
    public GameTime getEnd() {
        return end;
    }
    
    /**
     * checks for if the input time is within the period
     * @param other the time to be compared against
     * @return true iff time is within the start and end time
     */
    public boolean isWithin(GameTime other) {
        return other.isSoonerThan(this.start) || other.isLaterThan(this.end);
    }
    
}
