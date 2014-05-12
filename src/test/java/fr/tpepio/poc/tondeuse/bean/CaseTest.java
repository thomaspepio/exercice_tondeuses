package fr.tpepio.poc.tondeuse.bean;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author tPepio
 *
 */
public class CaseTest {

	@Test
	public void testEquality() {
		Case caseA = new Case(0, 0);
		caseA.mow();

		Case caseB = new Case(0, 0);

		int hashA = caseA.hashCode();
		int hashB = caseB.hashCode();

		Assert.assertEquals(caseA, caseB);
		Assert.assertEquals(hashA, hashB);
	}

	@Test
	public void testUnequality() {
		Case caseA = new Case(0, 0);
		caseA.mow();
		Case caseB = new Case(0, 1);
		caseB.mow();

		boolean expected = false;
		boolean actual = caseA.equals(caseB);

		int hashA = caseA.hashCode();
		int hashB = caseB.hashCode();

		Assert.assertEquals(expected, actual);
		Assert.assertNotSame(hashA, hashB);
	}

}
