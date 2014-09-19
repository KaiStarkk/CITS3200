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
public class GamePeriodTest {
	
	public GamePeriodTest() {
	}

	/**
	 * Test of isWithin method, of class GamePeriod.
	 */
	@Test
	public void testIsWithin() {
		System.out.println("isWithin");
		GameTime other = null;
		GamePeriod instance = null;
		boolean expResult = false;
		boolean result = instance.isWithin(other);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
