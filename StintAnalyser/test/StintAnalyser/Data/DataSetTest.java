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
 * @author Dean
 */
public class DataSetTest {
	private String pathToSource = "/home/dean/Documents/CITS3200/";
	
	private DataSet andrewDataSet;
	private DataSet aranDataSet;
	private DataSet chrisDataSet;
	
	public DataSetTest() {	
		this.andrewDataSet = new DataSet(pathToSource + "StintAnalyser/Input/GPS Stint Detector/Data Files/Andrew Philpott 4721 201305031758.csv");
		this.aranDataSet = new DataSet(pathToSource + "StintAnalyser/Input/GPS Stint Detector/Data Files/Aran Zalewski 4727 201305031758.csv");
		this.chrisDataSet = new DataSet(pathToSource + "StintAnalyser/Input/GPS Stint Detector/Data Files/Chris Bausor 4711 201305031758.csv");
	}

	/**
	 * Test of getTimeColumn method, of class DataSet.
	 */
	@Test
	public void testGetTimeColumn() {
		Column<Integer> andrewColumn = this.andrewDataSet.getTimeColumn();
		Column<Integer> aranColumn = this.aranDataSet.getTimeColumn();
		Column<Integer> chrisColumn = this.chrisDataSet.getTimeColumn();
		
		int expected,
			result;
		
		//************Andrew file*****************
		System.out.println("Checking time column of andrew file.");
		//line 9 - first line of data
		expected = 1;
		result = andrewColumn.get(9 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 10 - second line of data
		expected = 7;
		result = andrewColumn.get(10 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 33905
		expected = 338965;
		result = andrewColumn.get(33905 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//last line of file
		expected = 802857;
		result = andrewColumn.get(80294 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		
		//************Aran file*****************
		System.out.println("Checking time column of aran file.");
		//line 9 - first line of data
		expected = 1;
		result = aranColumn.get(9 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 10 - second line of data
		expected = 8;
		result = aranColumn.get(10 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 20430
		expected = 204220;
		result = aranColumn.get(20430 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//last line of file
		expected = 861251;
		result = aranColumn.get(86133 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//************Chris file*****************
		System.out.println("Checking time column of chris file.");
		//line 9 - first line of data
		expected = 1;
		result = chrisColumn.get(9 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 10 - second line of data
		expected = 8;
		result = chrisColumn.get(10 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 55271
		expected = 552624;
		result = chrisColumn.get(55271 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//last line of file
		expected = 804756;
		result = chrisColumn.get(80484 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
	}

	/**
	 * Test of getGPStimeColumn method, of class DataSet.
	 */
	@Test
	public void testGetGPStimeColumn() {
		Column<Integer> andrewColumn = this.andrewDataSet.getGPStimeColumn();
		Column<Integer> aranColumn = this.aranDataSet.getGPStimeColumn();
		Column<Integer> chrisColumn = this.chrisDataSet.getGPStimeColumn();
		
		int  expected,
				result;
		
		//************Andrew file*****************
		System.out.println("Checking GPS time column of andrew file.");
		//line 9 - first line of data
		expected = -1;
		result = andrewColumn.get(9 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 10 - second line of data
		expected =  -1;
		result = andrewColumn.get(10 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 33905
		expected = 6808600;
		result = andrewColumn.get(33905 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//last line of file
		expected = 7272500;
		result = andrewColumn.get(80294 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		
		//************Aran file*****************
		System.out.println("Checking GPS time column of aran file.");
		//line 9 - first line of data
		expected =  -1;
		result = andrewColumn.get(9 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 10 - second line of data
		expected =  -1;
		result = andrewColumn.get(10 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 20430
		expected = 6679100;
		result = aranColumn.get(20430 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//last line of file
		expected = 7336100;
		result = aranColumn.get(86133 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//************Chris file*****************
		System.out.println("Checking GPS time column of chris file.");
		//line 9 - first line of data
		expected =  -1;
		result = andrewColumn.get(9 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 10 - second line of data
		expected =  -1;
		result = andrewColumn.get(10 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//line 55271
		expected = 7024600;
		result = chrisColumn.get(55271 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
		
		//last line of file
		expected = 7276700;
		result = chrisColumn.get(80484 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
	}

	/**
	 * Test of getGPSLatitudeColumn method, of class DataSet.
	 */
	@Test
	public void testGetGPSLatitudeColumn() {
		Column<Double> andrewColumn = this.andrewDataSet.getGPSLatitudeColumn();
		Column<Double> aranColumn = this.aranDataSet.getGPSLatitudeColumn();
		Column<Double> chrisColumn = this.chrisDataSet.getGPSLatitudeColumn();
		
		double expected,
			result;
		
		//************Andrew file*****************
		System.out.println("Checking latitude column of andrew file.");
		//line 9 - first line of data
		expected = -1.0;
		result = andrewColumn.get(9 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//line 10 - second line of data
		expected =  -1.0;
		result = andrewColumn.get(10 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//line 33905
		expected = -32.00034004;
		result = andrewColumn.get(33905 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//last line of file
		expected = -32.00033744;
		result = andrewColumn.get(80294 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		
		//************Aran file*****************
		System.out.println("Checking latitude column of aran file.");
		//line 9 - first line of data
		expected =  -1.0;
		result = andrewColumn.get(9 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//line 10 - second line of data
		expected =  -1.0;
		result = andrewColumn.get(10 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//line 20430
		expected = -32.00006784;
		result = aranColumn.get(20430 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//last line of file
		expected = -32.00013754;
		result = aranColumn.get(86133 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//************Chris file*****************
		System.out.println("Checking latitude column of chris file.");
		//line 9 - first line of data
		expected =  -1.0;
		result = andrewColumn.get(9 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//line 10 - second line of data
		expected =  -1.0;
		result = andrewColumn.get(10 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//line 55271
		expected = -31.99814384;
		result = chrisColumn.get(55271 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//last line of file
		expected = -32.00024754;
		result = chrisColumn.get(80484 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
	}

	/**
	 * Test of getGPSLongitudeColumn method, of class DataSet.
	 */
	@Test
	public void testGetGPSLongitudeColumn() {
		Column<Double> andrewColumn = this.andrewDataSet.getGPSLongitudeColumn();
		Column<Double> aranColumn = this.aranDataSet.getGPSLongitudeColumn();
		Column<Double> chrisColumn = this.chrisDataSet.getGPSLongitudeColumn();
		
		double expected,
			result;
		
		//************Andrew file*****************
		System.out.println("Checking longitude column of andrew file.");
		//line 9 - first line of data
		expected = -1.0;
		result = andrewColumn.get(9 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//line 10 - second line of data
		expected =  -1.0;
		result = andrewColumn.get(10 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//line 33905
		expected = 115.8917411;
		result = andrewColumn.get(33905 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//last line of file
		expected = 115.8912501;
		result = andrewColumn.get(80294 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		
		//************Aran file*****************
		System.out.println("Checking longitude column of aran file.");
		//line 9 - first line of data
		expected =  -1.0;
		result = andrewColumn.get(9 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//line 10 - second line of data
		expected =  -1.0;
		result = andrewColumn.get(10 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//line 20430
		expected = 115.8917406;
		result = aranColumn.get(20430 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//last line of file
		expected = 115.8915252;
		result = aranColumn.get(86133 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//************Chris file*****************
		System.out.println("Checking longitude column of chris file.");
		//line 9 - first line of data
		expected =  -1.0;
		result = andrewColumn.get(9 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//line 10 - second line of data
		expected =  -1.0;
		result = andrewColumn.get(10 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//line 55271
		expected = 115.8886809;
		result = chrisColumn.get(55271 - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
		
		//last line of file
		expected = 115.8912447;
		result = chrisColumn.get(80484 - 9);
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
	}
	
}
