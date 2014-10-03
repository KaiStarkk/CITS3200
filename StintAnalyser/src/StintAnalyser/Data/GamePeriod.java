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
    
    public GamePeriod(String line) throws IllegalArgumentException {
        String[] times = line.split("-");
        if (times.length != 2) {
            throw new IllegalArgumentException();
        }
        this.start = new GameTime(times[0]);
        this.end = new GameTime(times[1]);
    }
    
    public GamePeriod(GameTime start, GameTime end) {
        this.start = start;
        this.end = end;
    }
    
    public GameTime getStart() {
        return start;
    }
    
    public GameTime getEnd() {
        return end;
    }
    
    public boolean isWithin(GameTime other) {
        return other.isSoonerThan(this.start) || other.isLaterThan(this.end);
    }
    
}
