package StintAnalyser.Data;

/**
 * CITS3200 Professional Computing
 * GameTime.java
 * A point of time in a match.
 * Provides simple functions to parse strings into GameTimes, and compare 
 * GameTimes to one another.
 * @version 1.0 19/09/14
 * @author Group B
 */
public class GameTime {
    int hour;
    int minute;
    int second;
    
    /**
     * Constructor for Gametime
     * @param in the input string 
     * @throws java.lang.IllegalArgumentException thrown if length is of incorrect size
     */
    public GameTime(String in) throws java.lang.IllegalArgumentException {
        String[] parts = in.split(":");
        if (parts.length != 3) {
            throw new IllegalArgumentException();
        }
        try {
            for (String part : parts) {
                if (part.length() != 2 || Integer.parseInt(part) >= 60) {
                    throw new IllegalArgumentException();
                }
            }
            hour = Integer.parseInt(parts[0]);
            minute = Integer.parseInt(parts[1]);
            second = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * checks for if the GameTime is sooner than the input time
     * @param other the time to be compared against
     * @return true iff given time is sooner than the Gametime
     */
    public boolean isSoonerThan(GameTime other) {
        return this.hour > other.hour ? false : this.minute > other.minute ? false : this.second < other.second;
    }
    
    /**
     * checks for if the GameTime is sooner than the input time
     * @param otherStr the time to be compared against
     * @return true iff given time is sooner than the Gametime
     */
    public boolean isSoonerThan(String otherStr) {
        GameTime other = new GameTime(otherStr);
        return this.isSoonerThan(other);
    }
    
    /**
     * checks for if the GameTime is later than the input time
     * @param other the time to be compared against
     * @return true iff given time is later than the Gametime
     */
    public boolean isLaterThan(GameTime other) {
        return this.hour < other.hour ? false : this.minute < other.minute ? false : this.second > other.second;
    }
    
    /**
     * checks for if the GameTime is later than the input time
     * @param otherStr the time to be compared against
     * @return true iff given time is later than the Gametime
     */
    public boolean isLaterThan(String otherStr) {
        GameTime other = new GameTime(otherStr);
        return this.isLaterThan(other);
    }
    
    
    @Override
    public String toString() {    
        return hour + ":" + minute + ":" + "00.00";
    }
}
