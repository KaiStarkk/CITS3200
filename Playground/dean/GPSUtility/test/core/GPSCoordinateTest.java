/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dean
 */
public class GPSCoordinateTest {

    public GPSCoordinateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of latitude method, of class GPSCoordinate.
     */
    @Test
    public void testLatitude() {
	System.out.println("testing valid latitude");
	GPSCoordinate alpha = new GPSCoordinate(-37.925993, 145.187948);
	assertEquals(Math.toRadians(-37.925993), alpha.latitude(), 0);
    }

    /**
     * Test of latitude method, of class GPSCoordinate.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLatitude() {
	System.out.println("testing invalid latitude");
	GPSCoordinate alpha = new GPSCoordinate(-137.925993, 145.187948);
    }

    /**
     * Test of longitude method, of class GPSCoordinate.
     */
    @Test
    public void testLongitude() {
	System.out.println("testing invalid longitude");
	GPSCoordinate alpha = new GPSCoordinate(-37.925993, 145.187948);
	assertEquals(Math.toRadians(145.187948), alpha.longitude(), 0);
    }

    /**
     * Test of longitude method, of class GPSCoordinate.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLongitude() {
	System.out.println("testing invalid longitude");
	GPSCoordinate alpha = new GPSCoordinate(-37.925993, 245.187948);
    }

    /**
     * Test of distanceTo method, of class GPSCoordinate.
     */
    @Test
    public void testDistanceAndBearingTo() {
	System.out.println("distanceTo(): checking valid calculations");

	GPSCoordinate alpha = new GPSCoordinate(-37.925993, 145.187948);
	GPSCoordinate beta = new GPSCoordinate(-37.925226, 145.189557);
	double distance = alpha.distanceTo(beta);
	double bearing = alpha.bearingTo(beta);
	System.out.println(bearing);
	assertEquals(164.9, distance, 0.1);
	assertEquals(Math.toRadians(58.855000), bearing, 0.001);

	alpha = new GPSCoordinate(43.26388889, -2.948011111);
	beta = new GPSCoordinate(43.26312222, -2.948766667);
	distance = alpha.distanceTo(beta);
	bearing = alpha.bearingTo(beta);
	assertEquals(104.9, distance, 0.1);
	assertEquals(Math.toRadians(215.665556), bearing, 0.001);

	alpha = new GPSCoordinate(-35, -150);
	beta = new GPSCoordinate(-35, -150.0018);
	distance = alpha.distanceTo(beta);
	bearing = alpha.bearingTo(beta);
	assertEquals(164.0, distance, 0.1);
	assertEquals(Math.toRadians(269.999444), bearing, 0.001);
    }
}
