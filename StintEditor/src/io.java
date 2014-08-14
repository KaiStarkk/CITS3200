/**
 * CITS3200 Professional Computing
 * io.java
 * Purpose: This class is concerned with the input/output aspect of the program.
 *          It reads in a *.raw file and outputs a *.vin file.
 *
 * @version 1.0 07/08/14
 * @author Group B
 */

import java.util.*;
import java.io.*;


public class io{
	
	String outputdirectory;

	public io(){

		File current = new File("io.java");
		String abs = current.getAbsolutePath();
		abs = abs.replace("src\\io.java","Output\\");
		outputdirectory = abs;

	}
	

	public void getPlayerData(Stintdata stints,String player) throws IOException {

		String[] out = {"example","second"};
	
		//do something to format data into arrays of strings 
		this.printArrayData(out,player); 
	}
	/*

	prints out the already formed string data
	*/
	private void printArrayData(String[] array, String filename) throws IOException {
		
		FileOutputStream out = new FileOutputStream(outputdirectory+filename+".vid");
		
		//will be changed
		for(int i = 0;i<array.length;i++){
			
			String outstring = array[i];
			out.write(outstring.getBytes());
			out.flush();
		}
		
	}
	

}

/**
 *
 *
 *
 */
class Data{
	

	String[] data;
	
	public Data(String gps,String time,String accel){
		
		data = new String[3];
		data[0]= gps;
		data[1] = time;
		data[2] = accel;
        
	}
	public toString(){
		
		return "";
	}
    
}

/**
 *
 *
 *
 *
 */
class Dataset{
	
	int[][] timegps;
	int[][] timeaccel;
	int[][] gpstime;
	
	public Dataset(Data[] set){

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