/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StintAnalyser.Analysis.Heuristics;

import StintAnalyser.Data.DataSet;
import StintAnalyser.Grounds.GPSCoordinate;
import StintAnalyser.Grounds.Ground;
import StintAnalyser.Stints.Stint;
import StintAnalyser.Stints.StintSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dean
 */
public class GPSAnalyserTest {
	private DataSet andrew;
	private DataSet aran;
	private DataSet chris;
	
	private Ground perthStadiumA;
	private Ground perthStadiumB;
	
	private int[] andrewExpectedStartTimes;
	private int[] andrewExpectedEndTimes;
	
	private int[] aranExpectedStartTimes;
	private int[] aranExpectedEndTimes;
	
	private int[] chrisExpectedStartTimes;
	private int[] chrisExpectedEndTimes;
	
	public GPSAnalyserTest() {
		this.andrew = new DataSet("/home/dean/Documents/CITS3200/StintAnalyser/Input/GPS Stint Detector/Data Files/Andrew Philpott 4721 201305031758.csv");
		this.aran = new DataSet("/home/dean/Documents/CITS3200/StintAnalyser/Input/GPS Stint Detector/Data Files/Aran Zalewski 4727 201305031758.csv");
		this.chris = new DataSet("/home/dean/Documents/CITS3200/StintAnalyser/Input/GPS Stint Detector/Data Files/Chris Bausor 4711 201305031758.csv");
		
		this.perthStadiumA = new Ground(
				"Perth Stadium A", 
				new GPSCoordinate(-31.9986542, 115.8906472), 
				new GPSCoordinate(-31.9994736, 115.8906417), 
				0.5856353641
		);
		
		this.perthStadiumB = new Ground(
				"Perth Stadium B", 
				new GPSCoordinate(-31.9999889, 115.8916167), 
				new GPSCoordinate(-32.0008056, 115.891625), 
				0.6022099257
		);
		
		
		int andrewExpectedStartTimes[] = new int[]{
			321563,
			407934,
			616485,
			703719,
		};
		
		int andrewExpectedEndTimes[] = new int[]{
			382859,
			475256,
			678839,
			751208,
		};
		
		int aranExpectedStartTimes[] = new int[]{
			286250,
			338245,
			441123,
			591954,
			692720,
		};
		
		int aranExpectedEndTimes[] = new int[]{
			307682,
			399233,
			496511,
			643866,
			744726,
		};
		
		int chrisExpectedStartTimes[] = new int[]{
			313721,
			391529,
			566402,
			671361,
			758628,
		};
		
		int chrisExpectedEndTimes[] = new int[]{
			366392,
			472980,
			642987,
			728521,
			780923,
		};
	}

	/**
	 * Test of findStints method, of class GPSAnalyser.
	 */
	@Test
	public void testFindStints() {
		GPSAnalyser analyser1 = new GPSAnalyser(andrew, perthStadiumA);
		GPSAnalyser analyser2 = new GPSAnalyser(aran, perthStadiumB);
		GPSAnalyser analyser3 = new GPSAnalyser(chris, perthStadiumB);
		
		StintSet andrewsResults = analyser1.findStints();
		StintSet aransResults = analyser2.findStints();
		StintSet chrisResults = analyser3.findStints();
		
		for (int i = 0; i < andrewsResults.size(); i++) {
			System.out.println("Comparing Andrew's results, stint: " + i + "**************************");
			System.out.println("Expected Start time: " + andrewsResults.getStint(i).getStartTime() +
					", Actual Start time: " + andrewExpectedStartTimes[i]);
			System.out.println("Expected End time: " + andrewsResults.getStint(i).getEndTime() +
					", Actual Start time: " + andrewExpectedEndTimes[i]);
			assertEquals(andrewsResults.getStint(i).getStartTime(), andrewExpectedStartTimes[i]);
			assertEquals(andrewsResults.getStint(i).getEndTime(), andrewExpectedEndTimes[i]);
		}
		
		for (int i = 0; i < andrewsResults.size(); i++) {
			System.out.println("Comparing Aran's results, stint: " + i + "**************************");
			System.out.println("Expected Start time: " + aransResults.getStint(i).getStartTime() +
					", Actual Start time: " + aranExpectedStartTimes[i]);
			System.out.println("Expected End time: " + aransResults.getStint(i).getEndTime() +
					", Actual Start time: " + aranExpectedEndTimes[i]);
			assertEquals(aransResults.getStint(i).getStartTime(), aranExpectedStartTimes[i]);
			assertEquals(aransResults.getStint(i).getEndTime(), aranExpectedEndTimes[i]);
		}
		
		for (int i = 0; i < andrewsResults.size(); i++) {
			System.out.println("Comparing Chris' results, stint: " + i + "**************************");
			System.out.println("Expected Start time: " + chrisResults.getStint(i).getStartTime() +
					", Actual Start time: " + chrisExpectedStartTimes[i]);
			System.out.println("Expected End time: " + chrisResults.getStint(i).getEndTime() +
					", Actual Start time: " + chrisExpectedEndTimes[i]);
			assertEquals(chrisResults.getStint(i).getStartTime(), chrisExpectedStartTimes[i]);
			assertEquals(chrisResults.getStint(i).getEndTime(), chrisExpectedEndTimes[i]);
		}
	}
	
}
