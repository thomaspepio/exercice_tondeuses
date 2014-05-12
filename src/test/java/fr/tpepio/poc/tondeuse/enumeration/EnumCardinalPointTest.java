package fr.tpepio.poc.tondeuse.enumeration;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author tPepio
 *
 */
public class EnumCardinalPointTest {

	@Test
	public void testCode() {
		EnumCardinalPoint expected = EnumCardinalPoint.NORTH;

		String code = "N";
		EnumCardinalPoint actual = EnumCardinalPoint.fromCode(code);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetRightCardinalPoint() {
		EnumCardinalPoint initialPoint = EnumCardinalPoint.NORTH;

		EnumCardinalPoint expected = EnumCardinalPoint.EAST;
		EnumCardinalPoint actual = initialPoint.getRightCardinalPoint();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetLeftCardinalPoint() {
		EnumCardinalPoint initialPoint = EnumCardinalPoint.NORTH;

		EnumCardinalPoint expected = EnumCardinalPoint.WEST;
		EnumCardinalPoint actual = initialPoint.getLeftCardinalPoint();

		Assert.assertEquals(expected, actual);
	}

}
