/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StintAnalyser.Data;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dean
 */
public class DataSetTest {
	
	private DataSet andrewDataSet;
	private DataSet aranDataSet;
	private DataSet chrisDataSet;
	
	public DataSetTest() {
		System.out.println("Checking that DataSet returns number of randomly selected rows from the following player's files: +\n"
				+ "Andrew Philpot \n"
				+ "Aran Zalewski \n"
				+ "Chris Bausor.");
		
		this.andrewDataSet = new DataSet("/home/dean/Documents/CITS3200/StintAnalyser/Input/GPS Stint Detector/Data Files/Andrew Philpott 4721 201305031758.csv");
		this.aranDataSet = new DataSet("/home/dean/Documents/CITS3200/StintAnalyser/Input/GPS Stint Detector/Data Files/Aran Zalewski 4727 201305031758.csv");
		this.chrisDataSet = new DataSet("/home/dean/Documents/CITS3200/StintAnalyser/Input/GPS Stint Detector/Data Files/Chris Bausor 4711 201305031758.csv");
	}

	/**
	 * Test of getTimeColumn method, of class DataSet.
	 */
	@Test
	public void testGetTimeColumn() {
		Column<Integer> andrewTimeColum = this.andrewDataSet.getTimeColumn();
		Column<Integer> aranTimeColum = this.aranDataSet.getTimeColumn();
		Column<Integer> chrisTimeColum = this.chrisDataSet.getTimeColumn();
		
		int expected,
			result;
		
		//************Andrew file*****************
		//line 9 - first line of data
		expected = 1;
		result = andrewTimeColum.get(9 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 10 - second line of data
		expected = 7;
		result = andrewTimeColum.get(10 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 33905
		expected = 338965;
		result = andrewTimeColum.get(33905 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//last line of file
		expected = 802857;
		result = andrewTimeColum.get(80294 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		
		//************Aran file*****************
		//line 9 - first line of data
		expected = 1;
		result = aranTimeColum.get(9 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 10 - second line of data
		expected = 8;
		result = aranTimeColum.get(10 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 20430
		expected = 204220;
		result = aranTimeColum.get(20430 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//last line of file
		expected = 861251;
		result = aranTimeColum.get(86133 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//************Chris file*****************
		//line 9 - first line of data
		expected = 1;
		result = chrisTimeColum.get(9 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 10 - second line of data
		expected = 8;
		result = chrisTimeColum.get(10 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 55271
		expected = 552624;
		result = chrisTimeColum.get(55271 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//last line of file
		expected = 804756;
		result = chrisTimeColum.get(80484 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
	}

	/**
	 * Test of getPlayerLoadColumn method, of class DataSet.
	 */
	@Test
	public void testGetPlayerLoadColumn() {
		System.out.println("getPlayerLoadColumn");
		DataSet instance = null;
		Column expResult = null;
		Column result = instance.getPlayerLoadColumn();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getGPStimeColumn method, of class DataSet.
	 */
	@Test
	public void testGetGPStimeColumn() {
		System.out.println("getGPStimeColumn");
		DataSet instance = null;
		Column expResult = null;
		Column result = instance.getGPStimeColumn();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getGPSLatitudeColumn method, of class DataSet.
	 */
	@Test
	public void testGetGPSLatitudeColumn() {
		System.out.println("getGPSLatitudeColumn");
		DataSet instance = null;
		Column expResult = null;
		Column result = instance.getGPSLatitudeColumn();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getGPSLongitudeColumn method, of class DataSet.
	 */
	@Test
	public void testGetGPSLongitudeColumn() {
		System.out.println("getGPSLongitudeColumn");
		DataSet instance = null;
		Column expResult = null;
		Column result = instance.getGPSLongitudeColumn();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
