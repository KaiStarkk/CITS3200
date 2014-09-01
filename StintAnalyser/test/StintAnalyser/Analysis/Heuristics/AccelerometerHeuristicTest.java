package StintAnalyser.Analysis.Heuristics;

import StintAnalyser.Data.DataSet;
import StintAnalyser.Stints.StintSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dean
 */
public class AccelerometerHeuristicTest {
    
    public AccelerometerHeuristicTest() {
    }

    /**
     * Test of getResult method, of class AccelerometerHeuristic.
     */
    @org.junit.Test
    public void testGetResult() {
	System.out.println("getResult");
	DataSet data = null;
	StintSet expResult = null;
	StintSet result = AccelerometerHeuristic.getResult(data);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
    
}
