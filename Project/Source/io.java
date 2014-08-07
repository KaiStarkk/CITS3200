/**
 * CITS3200 Professional Computing
 * io.java
 * Purpose: This class is concerned with the input/output aspect of the program.
 *          It reads in a *.raw file and outputs a *.vin file.
 *
 * @version 1.0 07/08/14
 * @author Group B
 */

public class io{
	
	
	
	public io(){
		
	}
	
	
	public void printArrayData(Dataset array, String filename){
		
		FileOutputStream out = new FileOutputStream(filename+".vin");
		
		//will be changed
		for(int i = 0;i<array.internal.length;i++){
			
			String outstring = array.internal[i].toString()+"\n";
			out.write(outstring.getBytes());
		}
		
	}
}

/**
 * 
 * 
 *
 */
class Data{
	
	String gpsdata;
	String timedata;
	String acceldata;
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
	
	Data[] internal;
	
	public Dataset(Data[] set){
		internal = set;
	}
	
	// 0 - gps ,1 - time, 2 - accelerometers
	public String[] getGps(){
		
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
	}
	
}