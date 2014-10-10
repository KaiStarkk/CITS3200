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
    GPSCoordinate origin;
    GPSCoordinate testCoordinates[];
	
	public GPSCoordinateTest() {
		origin = new GPSCoordinate(0.0, 0.0);
		this.testCoordinates = new GPSCoordinate[]{
            new GPSCoordinate(0.005, 0.005), //NE
            new GPSCoordinate(-0.005, 0.005), //SE
            new GPSCoordinate(-0.005, -0.005), //SW
            new GPSCoordinate(0.005, -0.005), // NW 
			new GPSCoordinate(0.00065, 0.00065), //NE
            new GPSCoordinate(-0.00065, 0.00065), //SE
            new GPSCoordinate(-0.00065, -0.00065), //SW
            new GPSCoordinate(0.00065, -0.00065), // NW 
		};
	
	}
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
		GPSCoordinate testCoordinatesRotate[] = {
			new GPSCoordinate(0.0, 0.01), //SE shifted
            new GPSCoordinate(0.0, 0.0), //SW shifted
            new GPSCoordinate(0.01, 0.0), // NW shifted
			new GPSCoordinate(0.01, 0.01), //NE shifted
			
			new GPSCoordinate(0.0, 0.01), //SE shifted
            new GPSCoordinate(0.0, 0.0), //SW shifted
            new GPSCoordinate(0.01, 0.0), // NW shifted
			new GPSCoordinate(0.01, 0.01), //NE shifted
			
            new GPSCoordinate(-0.005, 0.005), //SE
            new GPSCoordinate(-0.005, -0.005), //SW
            new GPSCoordinate(0.005, -0.005), // NW 
			new GPSCoordinate(0.005, 0.005), //NE
			
			new GPSCoordinate(0.005, 0.005), //NE
            new GPSCoordinate(-0.005, 0.005), //SE
            new GPSCoordinate(-0.005, -0.005), //SW
            new GPSCoordinate(0.005, -0.005), // NW 
			
			new GPSCoordinate(0.005, 0.005), //NE
            new GPSCoordinate(-0.005, 0.005), //SE
            new GPSCoordinate(-0.005, -0.005), //SW
            new GPSCoordinate(0.005, -0.005), // NW 
			
			new GPSCoordinate(0.00065, 0.00065), //NE
            new GPSCoordinate(-0.00065, 0.00065), //SE
            new GPSCoordinate(-0.00065, -0.00065), //SW
            new GPSCoordinate(0.00065, -0.00065), // NW 
		};
		
        GPSCoordinate coordinatesExpected[] = {
			new GPSCoordinate(0.005 - 0.007071068, 0.005), //SE shifted -ve rotation
			new GPSCoordinate(0.005, 0.005 - 0.007071068), //SW shifted -ve rotation
			new GPSCoordinate(0.005 + 0.007071068, 0.005), //NW shifted -ve rotation
			new GPSCoordinate(0.005, 0.007071068 + 0.005), //NE shifted -ve rotation
			
			new GPSCoordinate(0.005, 0.007071068 + 0.005), //SE shifted
            new GPSCoordinate(0.005 - 0.007071068, 0.005), //SW shifted
            new GPSCoordinate(0.005, 0.005 - 0.007071068), // NW shifted
			new GPSCoordinate(0.005 + 0.007071068, 0.005), //NE shifted
			
            new GPSCoordinate(0.005, 0.005), //SE rotated 90
            new GPSCoordinate(-0.005, 0.005), //SW rotated 90
            new GPSCoordinate(-0.005, -0.005), // NW rotated 90
			new GPSCoordinate(0.005, -0.005), //NE rotated 90
			
            new GPSCoordinate(0.005, 0.005), //NE not rotated
            new GPSCoordinate(-0.005, 0.005), //SE not rotated
            new GPSCoordinate(-0.005, -0.005), //SW not rotated
            new GPSCoordinate(0.005, -0.005), // NW not rotated
			
			new GPSCoordinate(0.007071068, 0.0), //NE rotated 45 degrees
            new GPSCoordinate(0.0, 0.007071068), //SE rotated 45 degrees
            new GPSCoordinate(-0.007071068, 0.0), //SW rotated 45 degrees
            new GPSCoordinate(0.0, -0.007071068), // NW rotated 45 degrees
			
			new GPSCoordinate(0.000919239, 0.0), //NE rotated 45 degrees
            new GPSCoordinate(0.0, 0.000919239), //SE rotated 45 degrees 
            new GPSCoordinate(-0.000919239, 0.0), //SW rotated 45 degrees 
            new GPSCoordinate(0.0, -0.000919239), // NW rotated 45 degrees 
        };
		
		GPSCoordinate origin2 = new GPSCoordinate(0.005, 0.005);
		
		System.out.println("Testing rotate() function with a bearing of -45.0 with shifted origin");
		for (int i = 0; i < 4; i++) {
            GPSCoordinate expResult = coordinatesExpected[i];
            GPSCoordinate result = origin2.rotate(testCoordinatesRotate[i], -45.0);
			System.out.println("Expected Lat: " + expResult.latitude() + ", Actual Lat: " + result.latitude());
			System.out.println("Expected Lon: " + expResult.longitude() + ", Actual Lon: " + result.longitude());
			System.out.println("");
            assertEquals(expResult.latitude(), result.latitude(), 0.000001);
			assertEquals(expResult.longitude(), result.longitude(), 0.000001);
        }
		
		System.out.println("Testing rotate() function with a bearing of 45.0 with shifted origin");
		for (int i = 4; i < 8; i++) {
            GPSCoordinate expResult = coordinatesExpected[i];
            GPSCoordinate result = origin2.rotate(testCoordinatesRotate[i], 45.0);
			System.out.println("Expected Lat: " + expResult.latitude() + ", Actual Lat: " + result.latitude());
			System.out.println("Expected Lon: " + expResult.longitude() + ", Actual Lon: " + result.longitude());
			System.out.println("");
            assertEquals(expResult.latitude(), result.latitude(), 0.000001);
			assertEquals(expResult.longitude(), result.longitude(), 0.000001);
        }
		
		System.out.println("Testing rotate() function with a bearing of 90.0");
		for (int i = 8; i < 12; i++) {
            GPSCoordinate expResult = coordinatesExpected[i];
            GPSCoordinate result = this.origin.rotate(testCoordinatesRotate[i], 90.0);
			System.out.println("Expected Lat: " + expResult.latitude() + ", Actual Lat: " + result.latitude());
			System.out.println("Expected Lon: " + expResult.longitude() + ", Actual Lon: " + result.longitude());
			System.out.println("");
            assertEquals(expResult.latitude(), result.latitude(), 0.000001);
			assertEquals(expResult.longitude(), result.longitude(), 0.000001);
        }
		
		System.out.println("Testing rotate() function with a bearing of 0.0");
		for (int i = 12; i < 16; i++) {
            GPSCoordinate expResult = coordinatesExpected[i];
            GPSCoordinate result = this.origin.rotate(testCoordinatesRotate[i], 0.0);
			System.out.println("Expected Lat: " + expResult.latitude() + ", Actual Lat: " + result.latitude());
			System.out.println("Expected Lon: " + expResult.longitude() + ", Actual Lon: " + result.longitude());
			System.out.println("");
            assertEquals(expResult.latitude(), result.latitude(), 0.000001);
			assertEquals(expResult.longitude(), result.longitude(), 0.000001);
        }
		
		System.out.println("Testing rotate() function with a bearing of 45.0");
        for (int i = 16; i < testCoordinatesRotate.length; i++) {
            GPSCoordinate expResult = coordinatesExpected[i];
            GPSCoordinate result = this.origin.rotate(testCoordinatesRotate[i], 45.0);
			System.out.println("Expected Lat: " + expResult.latitude() + ", Actual Lat: " + result.latitude());
			System.out.println("Expected Lon: " + expResult.longitude() + ", Actual Lon: " + result.longitude());
			System.out.println("");
            assertEquals(expResult.latitude(), result.latitude(), 0.000001);
			assertEquals(expResult.longitude(), result.longitude(), 0.000001);
        }
    }
}
