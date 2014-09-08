/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package StintAnalyser.Analysis.Heuristics;

import stintanalyser.Grounds.GPSCoordinate;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dean
 */
public class GPSCoordinateTest {
    
    public GPSCoordinateTest() {
    }

    /**
     * Test of latitude method, of class GPSCoordinate.
     */
    @org.junit.Test
    public void testLatitude() {
	System.out.println("latitude");
	GPSCoordinate instance = null;
	double expResult = 0.0;
	double result = instance.latitude();
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of longitude method, of class GPSCoordinate.
     */
    @org.junit.Test
    public void testLongitude() {
	System.out.println("longitude");
	GPSCoordinate instance = null;
	double expResult = 0.0;
	double result = instance.longitude();
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of distanceTo method, of class GPSCoordinate.
     */
    @org.junit.Test
    public void testDistanceTo() {
	System.out.println("distanceTo");
	GPSCoordinate there = null;
	GPSCoordinate instance = null;
	double expResult = 0.0;
	double result = instance.distanceTo(there);
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of horizontalDisplacementTo method, of class GPSCoordinate.
     */
    @org.junit.Test
    public void testHorizontalDisplacementTo() {
	System.out.println("horizontalDisplacementTo");
	GPSCoordinate there = null;
	GPSCoordinate instance = null;
	double expResult = 0.0;
	double result = instance.horizontalDisplacementTo(there);
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of verticalDisplacementTo method, of class GPSCoordinate.
     */
    @org.junit.Test
    public void testVerticalDisplacementTo() {
	System.out.println("verticalDisplacementTo");
	GPSCoordinate there = null;
	GPSCoordinate instance = null;
	double expResult = 0.0;
	double result = instance.verticalDisplacementTo(there);
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of bearingTo method, of class GPSCoordinate.
     */
    @org.junit.Test
    public void testBearingTo() {
	System.out.println("bearingTo");
	GPSCoordinate there = null;
	GPSCoordinate instance = null;
	double expResult = 0.0;
	double result = instance.bearingTo(there);
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of rotate method, of class GPSCoordinate.
     */
    @org.junit.Test
    public void testRotate() {
	System.out.println("rotate");
	GPSCoordinate initialCoordinate = null;
	double transformationBearing = 0.0;
	GPSCoordinate instance = null;
	GPSCoordinate expResult = null;
	GPSCoordinate result = instance.rotate(initialCoordinate, transformationBearing);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
    
}
