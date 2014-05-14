package fr.tpepio.poc.tondeuse.strategy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.tpepio.poc.tondeuse.domain.Case;
import fr.tpepio.poc.tondeuse.domain.Grid;
import fr.tpepio.poc.tondeuse.enumeration.EnumCardinalPoint;
import fr.tpepio.poc.tondeuse.strategy.impl.DefaultMoveStrategy;
import fr.tpepio.poc.tondeuse.trans.Constants;

public class DefaultMoveStrategyTest {

	DefaultMoveStrategy defaultMoveStrategy = new DefaultMoveStrategy();

	Grid grid = null;

	@Before
	public void init() {
		this.grid = Mockito.mock(Grid.class);
		Mockito.doReturn(true).when(this.grid)
				.isInGrid(Matchers.any(Case.class));

	}

	@Test
	public void testMoveToNorth() {
		Case start = new Case(1, 1);
		Case arrival = this.defaultMoveStrategy.move(this.grid, start,
				EnumCardinalPoint.NORTH);

		Assert.assertNotNull("La case d'arrivée ne doit jamais être null.",
				arrival);

		Case expected = new Case(1, 2);
		Assert.assertEquals("La case d'arrivée est incorrecte.", expected,
				arrival);
	}

	@Test
	public void testMoveToEast() {
		Case start = new Case(1, 1);
		Case arrival = this.defaultMoveStrategy.move(this.grid, start,
				EnumCardinalPoint.EAST);

		Assert.assertNotNull("La case d'arrivée ne doit jamais être null.",
				arrival);

		Case expected = new Case(2, 1);
		Assert.assertEquals("La case d'arrivée est incorrecte.", expected,
				arrival);
	}

	@Test
	public void testMoveToSouth() {
		Case start = new Case(1, 1);
		Case arrival = this.defaultMoveStrategy.move(this.grid, start,
				EnumCardinalPoint.SOUTH);

		Assert.assertNotNull("La case d'arrivée ne doit jamais être null.",
				arrival);

		Case expected = new Case(1, 0);
		Assert.assertEquals("La case d'arrivée est incorrecte.", expected,
				arrival);
	}

	@Test
	public void testMoveToWest() {
		Case start = new Case(1, 1);
		Case arrival = this.defaultMoveStrategy.move(this.grid, start,
				EnumCardinalPoint.WEST);

		Assert.assertNotNull("La case d'arrivée ne doit jamais être null.",
				arrival);

		Case expected = new Case(0, 1);
		Assert.assertEquals("La case d'arrivée est incorrecte.", expected,
				arrival);
	}

	@Test
	public void testArrivalNotInGrid() {
		Mockito.doReturn(false).when(this.grid)
				.isInGrid(Matchers.any(Case.class));

		Case start = new Case(1, 1);
		Case arrival = this.defaultMoveStrategy.move(this.grid, start,
				EnumCardinalPoint.WEST);

		Assert.assertNotNull("La case d'arrivée ne doit jamais être null.",
				arrival);

		Case expected = Constants.ERROR_CASE;
		Assert.assertEquals("La case d'arrivée est incorrecte.", expected,
				arrival);
	}
}
