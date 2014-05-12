package fr.tpepio.poc.tondeuse.enumeration;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author tPepio
 *
 */
public class EnumDirectionTest {

	@Test
	public void testCode() {
		EnumMovement expected = EnumMovement.LEFT;

		String code = "G";
		EnumMovement actual = EnumMovement.fromCode(code);

		Assert.assertEquals(expected, actual);
	}

}
