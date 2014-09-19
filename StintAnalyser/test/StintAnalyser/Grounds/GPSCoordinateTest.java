/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StintAnalyser.Grounds;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author dean
 */
public class GPSCoordinateTest {
    GPSCoordinate origin = new GPSCoordinate(0.0, 0.0);
    GPSCoordinate testCoordinates[] = {
            new GPSCoordinate(0.005, 0.005), //NE
            new GPSCoordinate(-0.005, 0.005), //SE
            new GPSCoordinate(-0.005, -0.005), //SW
            new GPSCoordinate(0.005, -0.005), // NW 
			new GPSCoordinate(0.00065, 0.00065), //NE
            new GPSCoordinate(-0.00065, 0.00065), //SE
            new GPSCoordinate(-0.00065, -0.00065), //SW
            new GPSCoordinate(0.00065, -0.00065), // NW 
        };
    /**
     * Test of distanceTo method, of class GPSCoordinate.
     * Test based on mathematics found at http://www.movable-type.co.uk/scripts/latlong.html
     */
    @Test
    public void testDistanceTo() {
        System.out.println("Testing distanceTo() for a range of different GPS coordinates");
        double distancesExpected[] = {
            786.3,
            786.3,
            786.3,
            786.3,
			102.2,
			102.2,
			102.2,
			102.2
        };
		
        for (int i = 0; i < this.testCoordinates.length; i++) {
            double expResult = distancesExpected[i];
            double result = this.origin.distanceTo(this.testCoordinates[i]);
			System.out.println("Expected: " + expResult + ", Actual: " + result);
            assertEquals(expResult, result, 0.1);
        }
    }

    /**
     * Test of bearingTo method, of class GPSCoordinate.
     */
    @Test
    public void testBearingTo() {
        System.out.println("Testing bearingTo() for a range of different GPS coordinates");
        double bearingsExpected[] = {
            Math.PI/4,//45 deg Unit Cirlce
            7*Math.PI/4,//315 deg Unit Cirlce
            5*Math.PI/4,//225 deg Unit Cirlce
            3*Math.PI/4,//135 deg Unit Cirlce
			Math.PI/4,//45 deg Unit Cirlce
            7*Math.PI/4,//315 deg Unit Cirlce
            5*Math.PI/4,//225 deg Unit Cirlce
            3*Math.PI/4,//135 deg Unit Cirlce
        };
		
        for (int i = 0; i < this.testCoordinates.length; i++) {
            double expResult = bearingsExpected[i];
            double result = this.origin.bearingTo(this.testCoordinates[i]);
			System.out.println("Expected: " + expResult + ", Actual: " + result);
            assertEquals(expResult, result, 0.001);
        }
    }

    /**
     * Test of horizontalDisplacementTo method, of class GPSCoordinate.
     */
    @Test
    public void testHorizontalDisplacementTo() {
        System.out.println("Testing HorizontalDisplacementTo() for a range of different GPS coordinates");
        double distancesExpected[] = {
            555.998,
            555.998,
            -555.998,
            -555.998,
			72.266,
			72.266,
			-72.266,
			-72.266
        };
		
        for (int i = 0; i < this.testCoordinates.length; i++) {
            double expResult = distancesExpected[i];
            double result = this.origin.horizontalDisplacementTo(this.testCoordinates[i]);
			System.out.println("Expected: " + expResult + ", Actual: " + result);
            assertEquals(expResult, result, 0.1);
        }
    }

    /**
     * Test of verticalDisplacementTo method, of class GPSCoordinate.
     */
    @Test
    public void testVerticalDisplacementTo() {
        System.out.println("Testing verticleDisplacementTo() for a range of different GPS coordinates");
        double distancesExpected[] = {
            555.998,
            -555.998,
            -555.998,
            555.998,
			72.266,
			-72.266,
			-72.266,
			72.266
        };
		
        for (int i = 0; i < this.testCoordinates.length; i++) {
            double expResult = distancesExpected[i];
            double result = this.origin.verticalDisplacementTo(this.testCoordinates[i]);
			System.out.println("Expected: " + expResult + ", Actual: " + result);
            assertEquals(expResult, result, 0.1);
        }
    }

    /**
     * Test of rotate method, of class GPSCoordinate.
     */
    @Test
    public void testRotate() {
        System.out.println("Testing rotate() for a range of different GPS coordinates");
        GPSCoordinate coordinatesExpected[] = {
            new GPSCoordinate(0.0, 0.005), //NE rotated 45 degrees clockwise
            new GPSCoordinate(-0.005, 0.0), //SE rotated 45 degrees clockwise
            new GPSCoordinate(0.0, -0.005), //SW rotated 45 degrees clockwise
            new GPSCoordinate(0.005, 0.0), // NW rotated 45 degrees clockwise
			new GPSCoordinate(0.0, 0.00065), //NE rotated 45 degrees clockwise
            new GPSCoordinate(-0.00065, 0.0), //SE rotated 45 degrees clockwise
            new GPSCoordinate(0.0, -0.00065), //SW rotated 45 degrees clockwise
            new GPSCoordinate(0.00065, 0.0), // NW rotated 45 degrees clockwise
        };
		
        for (int i = 0; i < this.testCoordinates.length; i++) {
            GPSCoordinate expResult = coordinatesExpected[i];
            GPSCoordinate result = this.origin.rotate(this.testCoordinates[i], 45.0);
			System.out.println("Expected Lat: " + expResult.latitude() + ", Actual Lat: " + result.latitude());
			System.out.println("Expected Lon: " + expResult.longitude() + ", Actual Lon: " + result.longitude());
            assertEquals(expResult.latitude(), result.latitude(), 0.0001);
			assertEquals(expResult.longitude(), result.longitude(), 0.0001);
        }
    }
}
