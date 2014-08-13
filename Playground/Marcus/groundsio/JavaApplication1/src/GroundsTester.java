import java.util.*;


public class GroundsTester {


    public static void main(String[] args) {
        
        // Set us up an empty grounds database.
        GroundsDB emptyGDB = new GroundsDB();
        
        // Print its contents. Should be nothing!
        System.out.println("Printing emptyGBD:\n---");
        emptyGDB.printDB();
        
        // Now lets fill it with some data.
        emptyGDB.readIn("Grounds.txt");
        
        // And see what was added.
        System.out.println("Printing (not so) emptyGBD:\n---");
        emptyGDB.printDB();
        
        
        // Empty is not really a fitting name now. 
        // Let's make a new one with the same data.
        GroundsDB fullGDB = new GroundsDB("Grounds.txt");
        
        // It should have the same contents as the old DB.
        System.out.println("Printing fullGDB:\n---");
        fullGDB.printDB();
        
    }
}