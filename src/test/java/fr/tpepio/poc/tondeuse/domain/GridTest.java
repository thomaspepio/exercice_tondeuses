package fr.tpepio.poc.tondeuse.domain;

import org.junit.Assert;
import org.junit.Test;

public class GridTest {

	private Grid grid = new Grid(2, 2);

	@Test
	public void testCaseIsInGrid() {
		Case case1 = new Case(0, 0);
		Assert.assertTrue("La case devrait être dans la grille.",
				this.grid.isInGrid(case1));

		Case case2 = new Case(0, 1);
		Assert.assertTrue("La case devrait être dans la grille.",
				this.grid.isInGrid(case2));

		Case case3 = new Case(1, 0);
		Assert.assertTrue("La case devrait être dans la grille.",
				this.grid.isInGrid(case3));

		Case case4 = new Case(1, 1);
		Assert.assertTrue("La case devrait être dans la grille.",
				this.grid.isInGrid(case4));

		Case case5 = new Case(3, 0);
		Assert.assertFalse("La case ne devrait pas être dans la grille.",
				this.grid.isInGrid(case5));
	}

	@Test
	public void testSize() {
		Assert.assertEquals("La taille de la grid est incorrecte.",
				Integer.valueOf(9), grid.getSize());
	}

}
