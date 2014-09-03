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
        
        System.out.println("Adding to emptyGDB");
        emptyGDB.newGround(1.2,-1.5,0,1.2456,-3.746,0,0.7855,1,"testing");
        
        System.out.println("Writing out");
        emptyGDB.writeOut("out.txt");
        
        emptyGDB.printDB();
        
        emptyGDB.remove("testing");
        
        emptyGDB.printDB();
        
    }
}