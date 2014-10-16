/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StintAnalyser.Data;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dean
 */
public class DataSetTest {
	private String pathToSource = "/home/dean/Documents/CITS3200/"; //dean
//	private String pathToSource = "/CITS3200/"; //cameron
	
	private DataSet[] dataSets = {
		new DataSet(pathToSource + "StintAnalyser/Input/Perth Stadium A/Andrew Philpott 4721 201305031758.csv"),
		new DataSet(pathToSource + "StintAnalyser/Input/Perth Stadium B/Aran Zalewski 4727 201305031758.csv"),
		new DataSet(pathToSource + "StintAnalyser/Input/Adelaide SH/Dylan Wotherspoon 4710 201410041308.csv"),
	};
	
	private String[] playerNames = {
		"Andrew",
		"Aran",
		"Dylan"
	};

	private void testLineInt(int expected, Column column, int line) {
		int result = (Integer)column.get(line - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result);
	}
	
	private void testLineDouble(double expected, Column column, int line) {
		double result = (Double)column.get(line - 9); 
		System.out.println("Expected: " + expected + ", Result: " + result);
		assertEquals(expected, result, 0.0);
	}
	
	/**
	 * Test of getTimeColumn method, of class DataSet.
	 */
	@Test
	public void testGetTimeColumn() {
		DataSet[] dataSets = this.dataSets;
		
		int[][] linesSelected = {
			{9, 10, 33905, 80294}, //andrew
			{9, 10, 20430, 86133}, //aran
			{9, 10, 47163, 67504}, //dylan
		};
		
		int[][] expecteds = {
			{1, 7, 338965, 802857}, //andrew
			{1, 8, 204220, 861251}, //aran
			{1, 4, 471550, 674960}, //aran
		};
		
		for (int i = 0, l = dataSets.length; i < l; i++) {
			System.out.println("Testing time column for " + this.playerNames[i]);
			Column<Integer> column = dataSets[i].getTimeColumn();
			
			for (int j = 0; j < expecteds[i].length; j++) {
				this.testLineInt(expecteds[i][j], column, linesSelected[i][j]);
			}
		}
	}

	/**
	 * Test of getGPStimeColumn method, of class DataSet.
	 */
	@Test
	public void testGetGPStimeColumn() {
		DataSet[] dataSets = this.dataSets;
		
		int[][] linesSelected = {
			{9, 10, 33905, 80294}, //andrew
			{9, 10, 20430, 86133}, //aran
			{9, 10, 47163, 67504}, //dylan
		};
		
		int[][] expecteds = {
			{-1, -1, 6808600, 7272500}, //andrew
			{-1, -1, 6679100, 7336100}, //aran
			{-1, -1, 4660700, 4864100}, //aran
		};
		
		for (int i = 0, l = dataSets.length; i < l; i++) {
			System.out.println("Testing GPS time column for " + this.playerNames[i]);
			Column<Integer> column = dataSets[i].getGPStimeColumn();
			
			for (int j = 0; j < expecteds[i].length; j++) {
				this.testLineInt(expecteds[i][j], column, linesSelected[i][j]);
			}
		}
	}

	/**
	 * Test of getGPSLatitudeColumn method, of class DataSet.
	 */
	@Test
	public void testGetGPSLatitudeColumn() {
		DataSet[] dataSets = this.dataSets;
		
		int[][] linesSelected = {
			{9, 10, 33905, 80294}, //andrew
			{9, 10, 20430, 86133}, //aran
			{9, 10, 47163, 67504}, //dylan
		};
		
		double[][] expecteds = {
			{-1, -1, -32.00034004, -32.00033744}, //andrew
			{-1, -1, -32.00006784, -32.00013754}, //aran
			{-34.8424318, -34.8424318, -34.8423876, -34.8424154}, //aran
		};
		
		for (int i = 0, l = dataSets.length; i < l; i++) {
			System.out.println("Testing latitude column for " + this.playerNames[i]);
			Column<Integer> column = dataSets[i].getGPSLatitudeColumn();
			
			for (int j = 0; j < expecteds[i].length; j++) {
				this.testLineDouble(expecteds[i][j], column, linesSelected[i][j]);
			}
		}
	}

	/**
	 * Test of getGPSLongitudeColumn method, of class DataSet.
	 */
	@Test
	public void testGetGPSLongitudeColumn() {
		DataSet[] dataSets = this.dataSets;
		
		int[][] linesSelected = {
			{9, 10, 33905, 80294}, //andrew
			{9, 10, 20430, 86133}, //aran
			{9, 10, 47163, 67504}, //dylan
		};
		
		double[][] expecteds = {
			{-1, -1, 115.8917411, 115.8912501}, //andrew
			{-1, -1, 115.8917406, 115.8915252}, //aran
			{138.60764236, 138.60764236, 138.60844886, 138.60798516}, //aran
		};
		
		for (int i = 0, l = dataSets.length; i < l; i++) {
			System.out.println("Testing longitude column for " + this.playerNames[i]);
			Column<Integer> column = dataSets[i].getGPSLongitudeColumn();
			
			for (int j = 0; j < expecteds[i].length; j++) {
				this.testLineDouble(expecteds[i][j], column, linesSelected[i][j]);
			}
		}
	}
	
}
