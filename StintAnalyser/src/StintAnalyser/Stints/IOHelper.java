package StintAnalyser.Stints;

/**
 * CITS3200 Professional Computing io.java Purpose: This class is concerned with
 * the input/output aspect of the program. It reads in a *.raw file and outputs
 * a *.vid file.
 *
 * @version 1.0 07/08/14
 * @author Group B
 */
import java.io.*;

public class IOHelper {

    String outputdirectory;
    String inputdirectory;

    public IOHelper() {

        File current = new File("io.java");
        String abs = current.getAbsolutePath();
        inputdirectory = abs.replace("src\\io.java", "Input\\");
        outputdirectory = abs.replace("src\\io.java", "Output\\");
    }

    /**
     *
     * Reads in the stint data constructed by the Analyser the data is then
     * formatted into an Array of strings in the format of a vid file which is
     * then passed to the printArrayData method
     *
     * @param stints the datastructure which holds data for one or more stints
     * @param player The String containing the players name
     * @throws java.io.IOException
     *
     */
    public void printPlayerData(StintSet stints, String player) throws IOException {

        String[] out = {"example", "second"};

        //do something to format data into arrays of strings
        this.printArrayData(out, outputdirectory + player + ".vid");
    }

    /**
     **
     ** Constructs a new file while deleting any existing file with the same
     * name and prints an array of strings to it * * @param array the
     * datastructure which holds data for one or more stints
     ** @param filename The String containing the absolute or abstract path of
     * the file to output to *
	*
     */
    private void printArrayData(String[] array, String filename) throws IOException {

        //delete any previous file with the same name
        File file = new File(filename);
        file.delete();
        FileOutputStream out = new FileOutputStream(filename);

        for (int i = 0; i < array.length; i++) {

            String outstring = array[i];
            out.write(outstring.getBytes());
            out.flush();
        }

        out.close();
    }

    //unsure of what datastructure to use for now it just says Data // generic exception for now think of another for later
    public Data readRawData(String filename) throws Exception {

        File file = new File(filename);

        if (file.exists()) {
            String raw = this.readData(filename, file.length());
        } else {
            throw new Exception();
        }

        return null;
    }

    /**
     **
     ** Reads the contents of a file into a string * * @param The String
     * containing the absolute or abstract path of the file to read from
     ** @param size The integer denoting the size of the input file in bytes
     ** @return A string containing the contents of a file
	*
     */
    private String readData(String filename, long size) throws FileNotFoundException, IOException {

        /* byte[] bytes = new byte[size];
         FileInputStream in = new FileInputStream(filename);
         in.read(bytes);
         return new String(bytes); */
        return null;
    }

    /**
     *
     *
     *
     */
    class Data {

        String[] data;

        public Data(String gps, String time, String accel) {

            data = new String[3];
            data[0] = gps;
            data[1] = time;
            data[2] = accel;

        }

        public String toString() {
            return "";
        }

    }

    /**
     *
     *
     *
     *
     */
    class Dataset {

        int[][] timegps;
        int[][] timeaccel;
        int[][] gpstime;

        public Dataset(Data[] set) {

        }

            // 0 - gps ,1 - time, 2 - accelerometers
            /*public String[] getGps(){

         return getSpecific(0);
         }
         public String[] getTime(){

         return getSpecific(1);
         }
         public String[] getAcc(){

         return getSpecific(2);
         }

         private String[] getSpecific(int k){

         String[] output = new String[this.internal.length];

         for(int i =0;i<this.internal.length;i++){

         output[i] = this.internal[i].data[k];
         }
         return output;
         }*/
    }
}