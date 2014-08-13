import java.util.*;
import java.io.*;

/**
 * A class for maintaining a database of grounds information.
 * @author Marcus
 * @version 12/08/14
 **/

public class GroundsDB {

    List<String[]> grounds;
    
    /**
     * Constructor for an empty grounds database.
     */
    public GroundsDB() {
        grounds = new ArrayList<>();
    }
    
    /**
     * Constructor for a grounds database with initial population.
     * @param inputFile, path to the input file.
     */
    public GroundsDB(String inputFile) {
        grounds = new ArrayList<>();
        readIn(inputFile);
    }
    
    /**
     * Reads in a text file for entry into the database.
     * @param inputFile, path to the input file.
     */
    public final void readIn(String inputFile) {
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] current = line.split("\\t");
                /*
                At this point we need to do some checking on current
                to make sure it's safe. I've assinged the return of
                grounds to help make a start on this.
                
                Some ideas: duplicates - prompt for update?
                unrealistically large - ask for approval.
                unreallistically small - etc.
                */
                boolean add = grounds.add(current);
            }
        }
        catch (IOException e){
            System.err.println("Could not open file.");
        }
    }
    
    /**
     * Returns the database as a list.
     * @return List containing all the grounds as string arrays.
     */
    public final List<String[]> getDB() {
        return grounds;
    }
    
    /**
     * Prints a database to standard output.
     */
    public final void printDB() {
        if (!grounds.isEmpty()) {
            grounds.stream().forEach((entry) -> {
                System.out.println(Arrays.toString(entry));
            });
            System.out.println();
        } else {
            System.out.println("Grounds database is empty.\n");
        }
    }
}
