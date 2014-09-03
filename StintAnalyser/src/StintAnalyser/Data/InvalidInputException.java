
/**
 * CITS3200 Professional Computing
 * InvalidInputException.java
 * A custom exception used for determining if data collected from the 
 * csv files is valid.
 * @version 1.1 3/09/14
 * @author Group B
 */


package StintAnalyser.Data;


public class InvalidInputException extends Exception{


	 /** 
	 * Constructor.
     * Constructs the Exception with an error msg 
     * 
     * 
     * @param msg The string containing the description of the error
     */
	public InvalidInputException(String msg){

		super(msg);

	}
}