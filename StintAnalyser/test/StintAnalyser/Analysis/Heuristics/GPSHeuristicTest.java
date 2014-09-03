/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package StintAnalyser.Analysis.Heuristics;

import StintAnalyser.Data.DataSet;
import StintAnalyser.Grounds.Ground;
import StintAnalyser.Stints.StintSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dean
 */
public class GPSHeuristicTest {
    
    public GPSHeuristicTest() {
    }

    /**
     * Test of getResult method, of class GPSHeuristic.
     */
    @org.junit.Test
    public void testGetResult() {
	System.out.println("getResult");
	DataSet data = null;
	Ground ground = null;
	StintSet expResult = null;
	StintSet result = GPSHeuristic.getResult(data, ground);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
    
}
