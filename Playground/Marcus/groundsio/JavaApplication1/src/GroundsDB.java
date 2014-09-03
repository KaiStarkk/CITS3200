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
    
    public final void writeOut(String outputFile) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for(int i =0; i<grounds.size(); i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j<8; j++) {
                    sb.append(grounds.get(i)[j]);
                    sb.append('\t');
                }
                sb.append(grounds.get(i)[8]);
                
                String current = sb.toString();
                
                bw.write(current);
                bw.newLine();
            }
        }
        catch(IOException e) {
            System.err.println("Could not write to file");
        }
        
    }
    
    /**
     * Adds new ground to the list
     * @param long1
     * @param lat1
     * @param type1
     * @param long2
     * @param lat2
     * @param type2
     * @param last
     * @param type3
     * @param name 
     */
    public final void newGround(double long1, double  lat1, int type1, double long2, double lat2, int type2, double last, int type3, String name) {
        String[] addNew = new String[9];
        
        addNew[0] = String.valueOf(long1);
        addNew[1] = String.valueOf(lat1);
        addNew[2] = String.valueOf(type1);
        addNew[3] = String.valueOf(long2);
        addNew[4] = String.valueOf(lat2);
        addNew[5] = String.valueOf(type2);
        addNew[6] = String.valueOf(last);
        addNew[7] = String.valueOf(type3);
        addNew[8] = name;
        
        grounds.add(addNew);
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
        List<String[]> temp = grounds;
        if (!temp.isEmpty()) {
            temp.stream().forEach((entry) -> {
                System.out.println(Arrays.toString(entry));
            });
            System.out.println();
        } else {
            System.out.println("Grounds database is empty.\n");
        }
    }
    
    /**
     * removes the specified string
     * @param rem 
     */
    public final void remove(String rem) {
        int i;
        for(i = 0; i<grounds.size(); i++) {
            if(rem.equals(grounds.get(i)[8])) {
                break;
            }
        }
        
        grounds.remove(i);
        
    }
}
