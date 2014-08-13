import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * A class that takes an input for the grounds and returns a list of grounds
 * @author Marcus
 * @version 12/08/14
 **/

public class groundio {
    
    String input;
    List<String[]> grounds;
    
    /**
     * constructor for the groundio
     * @param inputFile, the input filepath
     */
    public groundio(String inputFile) {
        input = inputFile;
        grounds = new ArrayList<String[]>();
    }
    
    /**
     * reads in the txt file for entry into the database
     * @return List containing all the grounds
     */
    public List<String[]> read() {
        try(BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line = br.readLine();
            String[] current = new String[9];

            while (line != null) {
                int i = 0;
                int index = 0;
                StringBuilder sb = new StringBuilder();
                
                while(i < line.length()) {
                    if(line.charAt(i) != '\t') {
                        sb.append(line.charAt(i));
                        String temp = sb.toString();
                    }
                    else {
                        String temp = sb.toString();
                        current[index] = temp;
                        index++;
                        sb = new StringBuilder();
                    }
                    i++;
                }
                
                //last string for grounds name
                String temp = sb.toString();
                current[index] = temp;
                
                line = br.readLine();
                System.out.println(Arrays.toString(current));
                grounds.add(current);
                
                /*for (int k =0; k<grounds.size(); k++) {
                    String[] strArr = grounds.get(k);
                    System.out.println(Arrays.toString(strArr));
                }*/
                
            }
        }
        catch (IOException e){
            System.err.println("could not open file");
        }
        return grounds;
    }
    

}
