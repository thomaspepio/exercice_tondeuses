package fr.tpepio.poc.tondeuse.bean;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author tPepio
 *
 */
public class GridTest {

	@Test
	public void testAddCase() {
		Case caseA = new Case(0, 0);
		caseA.mow();

		Case caseB = new Case(0, 0);

		Grid grid = new Grid(0, 1);
		grid.addCase(caseA);
		grid.addCase(caseB);

		int expectedSize = 1;
		int actualSize = grid.getNumberOfElements();

		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void testContainsCase() {
		Case caseA = new Case(0, 0);
		caseA.mow();

		Grid grid = new Grid(0, 1);
		grid.addCase(caseA);

		Case caseB = new Case(0, 0);

		boolean expected = true;
		boolean actual = grid.isInGrid(caseB);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testEquals() {
		// caseA et caseB sont égaux.
		Case caseA = new Case(0, 0);
		caseA.mow();
		Case caseB = new Case(0, 0);

		// caseC et caseD sont égaux.
		Case caseC = new Case(1, 1);
		caseC.mow();
		Case caseD = new Case(1, 1);

		Grid grid1 = new Grid(1, 1);
		grid1.addCase(caseA);
		grid1.addCase(caseC);
		Grid grid2 = new Grid(1, 1);
		grid2.addCase(caseB);
		grid2.addCase(caseD);

		int hash1 = grid1.hashCode();
		int hash2 = grid2.hashCode();

		Assert.assertEquals(grid1, grid2);
		Assert.assertEquals(hash1, hash2);
	}

	@Test
	public void testAddOverSize() {
		// Aucune case n'est égale à une autre.
		Case caseA = new Case(0, 0);
		Case caseB = new Case(0, 1);
		Case caseC = new Case(1, 0);
		Case caseD = new Case(1, 1);

		Grid grid = new Grid(0, 1);
		grid.addCase(caseA);
		grid.addCase(caseB);
		grid.addCase(caseC);
		grid.addCase(caseD);

		int expected = 2;
		int actual = grid.getNumberOfElements();

		Assert.assertEquals(expected, actual);
	}
}
