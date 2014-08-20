package StintAnalyser.Stints;

import java.nio.file.Path;
import java.util.ArrayList;

/**
 * CITS3200 Professional Computing
 * StintSet.java
 * Contains fields and methods pertaining to StintSets. StintSets are 
 * an array of Stints.
 * 
 * @version 1.1 20/08/14
 * @author Group B
 */
public class StintSet {
    
    private ArrayList<Stint> stintSet;
    
    /**
     * Empty constructor.
     */
    public StintSet() {
        
    }
    
    /**
     * Writes the StintSet out to a .vid file.
     * @param path path to write the file to.
     */
    public void writeToVid(Path path) {
        
    }
    
    /**
     * Reads into the StintSet from a .vid file.
     * @param path path to read the file from.
     */
    public void readFromCsv(Path path) {
        
    }
    
    /**
     * Get the StintSet's i'th item.
     * @param i the index of the Stint item to retrieve.
     * @return 
     */
    public Stint getStint(int i) {
        return stintSet.get(i);
    }
    
    /**
     * Add a new Stint to the StintSet
     * @param stint the stint to be added to the set.
     */
    public void addStint(Stint stint) {
        stintSet.add(stint);
    }
}
